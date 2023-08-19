package com.darren1112.dptms.sdk.starter.security.enums;


import com.darren1112.dptms.sdk.starter.security.base.processing.base.AuthType;
import com.darren1112.dptms.sdk.starter.security.core.security.token.PwdAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;

import java.util.Arrays;

/**
 * 认证类型枚举
 *
 * @author darren
 * @since 2022/6/6
 */
public enum AuthTypeEnum implements AuthType {

    /**
     * 认证枚举
     */
    USERNAME_AND_PASSWORD(1, "账号密码登录", PwdAuthenticationToken.class);

    /**
     * 认证类型
     */
    private Integer authType;

    /**
     * 认证说明
     */
    private String authDesc;

    /**
     * 认证对应的token类型
     */
    private Class<? extends BaseAuthenticationToken> tokenClass;

    AuthTypeEnum(Integer authType, String authDesc, Class<? extends BaseAuthenticationToken> tokenClass) {
        this.authType = authType;
        this.authDesc = authDesc;
        this.tokenClass = tokenClass;
    }

    /**
     * 根据认证类型匹配认证枚举
     *
     * @param authType 认证类型
     * @return {@link AuthTypeEnum}
     * @author darren
     * @since 2022/6/6
     */
    public static AuthTypeEnum matchByAuthType(Integer authType) {
        if (authType == null) {
            return null;
        }
        return Arrays.stream(AuthTypeEnum.values())
                .filter(item -> item.getAuthType().equals(authType))
                .findFirst().orElse(null);
    }

    /**
     * 认证类型
     *
     * @return {@link Integer}
     * @author darren
     * @since 2022/6/6
     */
    @Override
    public Integer getAuthType() {
        return this.authType;
    }

    /**
     * 认证说明
     *
     * @return {@link String}
     * @author darren
     * @since 2022/6/6
     */
    @Override
    public String getAuthDesc() {
        return this.authDesc;
    }

    /**
     * 认证对应的token类型
     *
     * @return {@link Class}
     * @author darren
     * @since 2022/6/6
     */
    @Override
    public Class<? extends BaseAuthenticationToken> getTokenClass() {
        return this.tokenClass;
    }
}
