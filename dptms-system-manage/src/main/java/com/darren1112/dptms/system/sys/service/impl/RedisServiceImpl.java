package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRedisDto;
import com.darren1112.dptms.system.sys.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * redis service impl
 *
 * @author luyuhao
 * @date 2021/01/31 00:05
 */
@Service
public class RedisServiceImpl extends BaseService implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据key的前缀分页查缓存数据
     *
     * @param keyPrefix key前缀
     * @param pageParam 分页参数
     * @return {@link SysRedisDto}
     * @author luyuhao
     * @date 2021/01/31 00:07
     */
    @Override
    public PageBean<SysRedisDto> listPage(String keyPrefix, PageParam pageParam) {
        Set<String> keySet = redisUtil.getKeysWithKeyPrefix("", keyPrefix);
        List<SysRedisDto> list = new ArrayList<>();
        for (String key : keySet) {
            Object value = redisUtil.getWithKeyPrefix("", key);
            long expire = redisUtil.getExpireWithKeyPrefix("", key);
            SysRedisDto subSysRedisDto = new SysRedisDto();
            subSysRedisDto.setKey(key);
            subSysRedisDto.setValue(value);
            subSysRedisDto.setExpired(expire);
            list.add(subSysRedisDto);
        }
        Long count = (long) list.size();
        list.sort(Comparator.comparing(SysRedisDto::getKey));
        int endIndex = pageParam.getStartIndex() + pageParam.getPageSize();
        endIndex = endIndex > list.size() ? list.size() : endIndex;
        list = list.subList(pageParam.getStartIndex(), endIndex);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 新增缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @date 2021/01/31 01:01
     */
    @Override
    public void insert(SysRedisDto sysRedisDto) {
        redisUtil.setWithKeyPrefix("", sysRedisDto.getKey(), sysRedisDto.getValue(), sysRedisDto.getExpired());
    }

    /**
     * 删除缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @date 2021/01/31 00:04
     */
    @Override
    public void deleteByKey(SysRedisDto sysRedisDto) {
        redisUtil.deleteWithKeyPrefix("", sysRedisDto.getKey());
    }
}
