package com.darren1112.dptms.sdk.starter.security.base.processing.provider;

import com.darren1112.dptms.sdk.starter.security.base.processing.base.AuthType;
import org.springframework.security.authentication.AuthenticationProvider;

/**
 * 认证校验器
 *
 * @author darren
 * @since 2022/06/05
 */
public abstract class BaseAuthenticationProvider implements AuthenticationProvider {

    /**
     * token对应的认证类型
     *
     * @return {@link AuthType}
     * @author darren
     * @since 2022/6/6
     */
    public abstract AuthType authType();
}
