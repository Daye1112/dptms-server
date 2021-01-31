package com.darren1112.dptms.common.spi.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 活跃(在线)用户信息
 *
 * @author luyuhao
 * @date 2020/11/22 16:59
 */
@Data
public class ActiveUser {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realName;

    /**
     * 性别 1：男 2：女
     */
    @ApiModelProperty("性别 1：男 2：女")
    private Integer gender;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    /**
     * 密码更新时间
     */
    @ApiModelProperty("密码更新时间")
    private Date pwdUpdateTime;

    /**
     * 头像文件id
     */
    @ApiModelProperty("头像文件id")
    private Long fileId;

    /**
     * 浏览器
     */
    @ApiModelProperty("浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String os;

    /**
     * ip
     */
    @ApiModelProperty("ip")
    private String ip;

    /**
     * ip地址
     */
    @ApiModelProperty("ip地址")
    private String ipAddress;

}
