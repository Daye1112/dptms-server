package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;

/**
 * 权限Service
 *
 * @author luyuhao
 * @date 2020/12/09 23:43
 */
public interface SysPermissionService {

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link Long 权限id}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long insert(SysPermissionEntity entity);
}
