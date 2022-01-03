package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.SysRoleDto;

import java.util.List;

/**
 * 用户角色Service
 *
 * @author luyuhao
 * @since 2020/12/23 01:51
 */
public interface SysUserRoleService {

    /**
     * 分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/23 01:48
     */
    void assignedRole(Long userId, String roleIds, Long updater);

    /**
     * 查询用户关联的角色list
     *
     * @param userId 用户id
     * @return {@link SysRoleDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    List<SysRoleDto> listUserAssigned(Long userId);
}
