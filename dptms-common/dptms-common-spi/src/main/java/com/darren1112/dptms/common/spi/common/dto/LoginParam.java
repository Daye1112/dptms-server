package com.darren1112.dptms.common.spi.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录参数
 *
 * @author luyuhao
 * @since 2020/11/22 17:18
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 登录类型 1：账号密码登录
     */
    @ApiModelProperty("登录类型 1：账号密码登录")
    private Integer loginType;
}
