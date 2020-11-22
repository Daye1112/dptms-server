package com.darren1112.dptms.common.spi.common.dto;

import lombok.Data;

/**
 * 登录参数
 *
 * @author luyuhao
 * @date 2020/11/22 17:18
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
