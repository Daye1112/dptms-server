package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;

/**
 * 角色菜单Service
 *
 * @author darren
 * @since 2020/12/13 23:09
 */
public interface AuthRoleMenuService {

    /**
     * 查询角色关联的菜单list
     *
     * @param roleId 角色id
     * @return {@link AuthMenuDto}
     * @author darren
     * @since 20/12/13 21:43
     */
    AuthMenuDto listRoleAssigned(Long roleId);

    /**
     * 分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单ids，逗号分隔
     * @param updater 更新者
     * @author darren
     * @since 20/12/13 22:10
     */
    void assignedMenu(Long roleId, String menuIds, Long updater);
}
