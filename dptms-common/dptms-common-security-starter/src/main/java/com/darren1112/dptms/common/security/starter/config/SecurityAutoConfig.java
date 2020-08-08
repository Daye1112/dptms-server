package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.security.starter.handler.DptmsAccessDeniedHandler;
import com.darren1112.dptms.common.security.starter.handler.DptmsAuthenticationEntryPoint;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * security自动配置
 *
 * @author luyuhao
 * @date 2020/08/04 01:35
 */
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public DptmsAccessDeniedHandler accessDeniedHandler() {
        return new DptmsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public DptmsAuthenticationEntryPoint authenticationEntryPoint() {
        return new DptmsAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * feign请求统一携带请求头
     *
     * @return 拦截器
     * @author luyuhao
     * @date 20/08/02 21:58
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
            requestTemplate.header(securityProperties.getHeaderKey(), zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}
