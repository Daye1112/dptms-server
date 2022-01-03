package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;

import java.util.List;

/**
 * 菜单权限service
 *
 * @author baojiazhong
 * @since 2020/12/22 22:38
 */
public interface AuthMenuPermissionService {

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto )
     * @author baojiazhong
     * @since 2020/12/22 22:41
     */
    List<AuthPermissionDto> listMenuAssigned(Long menuId);

    /**
     * 绑定权限
     *
     * @param menuId  菜单id
     * @param perIds  权限ids，逗号分隔
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/22 22:42
     */
    void assignedPer(Long menuId, String perIds, Long updater);

    /**
     * 根据菜单id查询权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2021/01/04 23:53
     */
    List<AuthPermissionDto> listByMenuId(Long menuId);
}