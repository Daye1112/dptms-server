package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @date 2020/07/23 02:42
 */
public interface SysUserService {

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/11/30 23:12
     */
    SysUserDto getById(Long id);
}
