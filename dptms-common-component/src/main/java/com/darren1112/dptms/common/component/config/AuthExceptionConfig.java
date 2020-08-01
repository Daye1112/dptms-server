package com.darren1112.dptms.common.component.config;

import com.darren1112.dptms.common.component.oauth2.DptmsAccessDeniedHandler;
import com.darren1112.dptms.common.component.oauth2.DptmsAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luyuhao
 * @date 2020/08/02 02:59
 */
@Configuration
public class AuthExceptionConfig {

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
}
