package com.darren1112.dptms.common.spi.common.entity;

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
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别 1：男 2：女
     */
    private Integer gender;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 头像文件id
     */
    private Long fileId;

    /**
     * 组织id
     */
    private Long orgId;

}
