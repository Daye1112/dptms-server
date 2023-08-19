package com.darren1112.dptms.auth.common.util;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 *
 * @author darren
 * @since 2021/01/13 00:56
 */
public class MenuUtil {

    /**
     * 生成树对象
     *
     * @param sysMenuList 菜单list
     * @return {@link AuthMenuDto}
     * @author darren
     * @since 2021/01/13 00:57
     */
    public static AuthMenuDto buildTree(List<AuthMenuDto> sysMenuList) {
        if (CollectionUtil.isEmpty(sysMenuList)) {
            return null;
        }
        for (AuthMenuDto dto : sysMenuList) {
            Long menuParentId = dto.getId();
            if (dto.getChildren() == null) {
                dto.setChildren(new ArrayList<>());
            }
            for (AuthMenuDto subDto : sysMenuList) {
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
