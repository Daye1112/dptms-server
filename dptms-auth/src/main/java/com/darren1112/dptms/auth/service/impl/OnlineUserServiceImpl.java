package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.service.OnlineUserService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 在线用户管理ServiceImpl
 *
 * @author luyuhao
 * @date 2021/01/31 22:05
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class OnlineUserServiceImpl extends BaseService implements OnlineUserService {

    @Autowired
    private DptmsTokenStore dptmsTokenStore;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页查询在线用户
     *
     * @param activeUser 查询条件
     * @param pageParam  分页参数
     * @return {@link ActiveUser}
     * @author luyuhao
     * @date 2021/01/31 21:52
     */
    @Override
    public PageBean<ActiveUser> listPage(ActiveUser activeUser, PageParam pageParam) {
        // 查询所有用户的refreshToken的user key
        List<String> keyList = dptmsTokenStore.listAllUserRefreshKeys();
        // 排序、总记录数、分页
        keyList.sort(String::compareTo);
        Long count = (long) keyList.size();
        int endIndex = pageParam.getStartIndex() + pageParam.getPageSize();
        endIndex = endIndex > keyList.size() ? keyList.size() : endIndex;
        keyList = keyList.subList(pageParam.getStartIndex(), endIndex);

        // 获取用户信息
        List<ActiveUser> activeUserList = new ArrayList<>();
        for (String key : keyList) {
            String refreshToken = Optional.ofNullable(redisUtil.getWithKeyPrefix("", key))
                    .map(Object::toString).orElse("");
            ActiveUser subActiveUser = dptmsTokenStore.getActiveUser(refreshToken);
            activeUserList.add(subActiveUser);
        }
        return createPageBean(pageParam, count, activeUserList);
    }

    /**
     * 强制下线用户
     *
     * @param userId 用户id
     * @author luyuhao
     * @date 2021/01/31 22:57
     */
    @Override
    public void forcedOffline(Long userId) {
        ActiveUser activeUser = new ActiveUser();
        activeUser.setId(userId);
        String userRefreshToken = dptmsTokenStore.getUserRefreshToken(activeUser);
        // 移除用户在线信息
        dptmsTokenStore.removeUserRefreshToken(activeUser);
        // 移除刷新token
        dptmsTokenStore.removeRefreshToken(userRefreshToken);
    }
}
