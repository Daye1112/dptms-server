package com.darren1112.dptms.sdk.starter.security.base.model;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 安全用户信息-抽象类
 *
 * @author darren
 * @since 2022/6/2
 */
public abstract class BaseSecurityUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 认证类型
     */
    private Integer authType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }
}
