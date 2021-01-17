package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;

import java.util.List;

/**
 * 菜单Service
 *
 * @author luyuhao
 * @since 2020/12/12 17:25
 */
public interface SysMenuService {

    /**
     * 获取用户的菜单
     *
     * @param userId 用户id
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    List<SysMenuDto> listMenuByUserId(Long userId);
}
