package com.darren1112.dptms.sdk.starter.security.base.processing.base;


import com.darren1112.dptms.sdk.starter.security.base.processing.token.BaseAuthenticationToken;

/**
 * 认证类型
 *
 * @author luyuhao
 * @since 2022/6/6
 */
public interface AuthType {

    /**
     * 认证类型
     *
     * @return {@link Integer}
     * @author luyuhao
     * @since 2022/6/6
     */
    Integer getAuthType();

    /**
     * 认证说明
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2022/6/6
     */
    String getAuthDesc();

    /**
     * 认证对应的token类型
     *
     * @return {@link Class}
     * @author luyuhao
     * @since 2022/6/6
     */
    Class<? extends BaseAuthenticationToken> getTokenClass();
}
