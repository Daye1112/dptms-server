package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;

import java.util.List;

/**
 * 菜单权限service
 *
 * @author baojiazhong
 * @since 2020/12/22 22:38
 */
public interface SysMenuPermissionService {

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto)
     * @author baojiazhong
     * @date 2020/12/22 22:41
     */
    List<SysPermissionDto> listMenuAssigned(Long menuId);

    /**
     * 绑定权限
     *
     * @param menuId  菜单id
     * @param perIds  权限ids，逗号分隔
     * @param updater 更新者
     * @author baojiazhong
     * @date 2020/12/22 22:42
     */
    void assignedPer(Long menuId, String perIds, Long updater);
}
