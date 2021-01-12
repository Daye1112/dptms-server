package com.darren1112.dptms.system.common.util;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 *
 * @author luyuhao
 * @date 2021/01/13 00:56
 */
public class MenuUtil {

    /**
     * 生成树对象
     *
     * @param sysMenuList 菜单list
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/13 00:57
     */
    public static SysMenuDto buildTree(List<SysMenuDto> sysMenuList) {
        if (CollectionUtil.isEmpty(sysMenuList)) {
            return null;
        }
        for (SysMenuDto dto : sysMenuList) {
            Long menuParentId = dto.getId();
            if (dto.getChildren() == null) {
                dto.setChildren(new ArrayList<>());
            }
            for (SysMenuDto subDto : sysMenuList) {
                if (subDto.getMenuParentId().equals(menuParentId)) {
                    dto.getChildren().add(subDto);
                }
            }
        }
        return sysMenuList.stream()
                .filter(item -> item.getMenuParentId().equals(0L))
                .findFirst().orElse(null);
    }
}
