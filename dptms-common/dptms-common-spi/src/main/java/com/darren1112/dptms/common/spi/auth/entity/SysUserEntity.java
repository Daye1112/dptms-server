package com.darren1112.dptms.common.spi.auth.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 系统用户Entity
 *
 * @author luyuhao
 * @since 20/07/23 02:19
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserEntity extends BaseEntity {

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
     * 盐
     */
    @ApiModelProperty("盐")
    private String salt;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 密码更新时间
     */
    @ApiModelProperty("密码更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwdUpdateTime;

    /**
     * 头像文件id
     */
    @ApiModelProperty("头像文件id")
    private Long fileId;

    /**
     * 是否被锁定 0：否 1：是
     */
    @ApiModelProperty("是否被锁定 0：否 1：是")
    private Integer isLocked;
}
