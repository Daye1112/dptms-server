package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;

/**
 * 角色菜单Service
 *
 * @author luyuhao
 * @since 2020/12/13 23:09
 */
public interface SysRoleMenuService {

    /**
     * 查询角色关联的菜单list
     *
     * @param roleId 角色id
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    SysMenuDto listRoleAssigned(Long roleId);

    /**
     * 分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/13 22:10
     */
    void assignedMenu(Long roleId, String menuIds, Long updater);
}
