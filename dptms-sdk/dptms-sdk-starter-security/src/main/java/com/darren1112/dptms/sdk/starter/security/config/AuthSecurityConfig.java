package com.darren1112.dptms.sdk.starter.security.config;

import com.darren1112.dptms.sdk.starter.security.core.security.handler.SimpleAccessDeniedHandler;
import com.darren1112.dptms.sdk.starter.security.core.security.handler.SimpleAuthenticationEntryPoint;
import com.darren1112.dptms.sdk.starter.security.core.security.handler.SimpleAuthenticationFailureHandler;
import com.darren1112.dptms.sdk.starter.security.core.security.handler.SimpleSessionInformationExpiredStrategy;
import com.darren1112.dptms.sdk.starter.security.core.security.encoder.Md5PasswordEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 认证服务自动装配
 *
 * @author darren
 * @since 2022/11/14
 */
public class AuthSecurityConfig {

    @ConditionalOnMissingBean
    @Bean("accessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    @ConditionalOnMissingBean
    @Bean("authenticationEntryPoint")
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    @ConditionalOnMissingBean
    @Bean("authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    @ConditionalOnMissingBean
    @Bean("sessionInformationExpiredStrategy")
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new SimpleSessionInformationExpiredStrategy();
    }

    @ConditionalOnMissingBean
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    /**
     * 默认角色前缀为"ROLE_", 将其设置为""
     *
     * @return {@link GrantedAuthorityDefaults}
     * @author darren
     * @since 2022/11/15
     */
    @ConditionalOnMissingBean
    @Bean("grantedAuthorityDefaults")
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
