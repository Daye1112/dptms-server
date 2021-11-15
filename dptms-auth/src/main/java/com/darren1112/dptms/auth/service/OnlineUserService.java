package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;

/**
 * 在线用户管理Service
 *
 * @author luyuhao
 * @since 2021/01/31 22:05
 */
public interface OnlineUserService {

    /**
     * 分页查询在线用户
     *
     * @param activeUser 查询条件
     * @param pageParam  分页参数
     * @return {@link ActiveUser}
     * @author luyuhao
     * @since 2021/01/31 21:52
     */
    PageBean<ActiveUser> listPage(ActiveUser activeUser, PageParam pageParam);

    /**
     * 强制下线用户
     *
     * @param userId 用户id
     * @author luyuhao
     * @since 2021/01/31 22:57
     */
    void forcedOffline(Long userId);
}
