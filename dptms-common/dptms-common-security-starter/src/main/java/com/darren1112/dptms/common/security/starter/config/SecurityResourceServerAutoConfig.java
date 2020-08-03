package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.security.starter.handler.DptmsAccessDeniedHandler;
import com.darren1112.dptms.common.security.starter.handler.DptmsAuthenticationEntryPoint;
import com.darren1112.dptms.common.security.starter.properties.GatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 * @author luyuhao
 * @date 2020/08/04 01:52
 */
public class SecurityResourceServerAutoConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private DptmsAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DptmsAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }


}
