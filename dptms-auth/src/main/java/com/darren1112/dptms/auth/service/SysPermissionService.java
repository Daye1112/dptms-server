package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;

import java.util.List;

/**
 * 权限Service
 *
 * @author luyuhao
 * @date 2020/12/09 23:43
 */
public interface SysPermissionService {

    /**
     * 根据用户id查询权限list
     *
     * @param userId 用户id
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/17 21:30
     */
    List<SysPermissionDto> listByUserId(Long userId);
}
