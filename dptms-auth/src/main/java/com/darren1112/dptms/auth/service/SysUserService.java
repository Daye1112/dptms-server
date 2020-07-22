package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.spi.auth.dto.SysUserDto;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @date 2020/07/23 02:42
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @date 20/07/23 02:58
     */
    SysUserDto getByUsername(String username);
}
