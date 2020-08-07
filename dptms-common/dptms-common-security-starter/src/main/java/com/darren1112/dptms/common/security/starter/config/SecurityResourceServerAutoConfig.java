package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.security.starter.handler.DptmsAccessDeniedHandler;
import com.darren1112.dptms.common.security.starter.handler.DptmsAuthenticationEntryPoint;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.Optional;

/**
 * 资源服务器配置
 *
 * @author luyuhao
 * @date 2020/08/04 01:52
 */
@EnableResourceServer
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class SecurityResourceServerAutoConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DptmsAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DptmsAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUris = Optional.ofNullable(securityProperties.getAnonUris()).orElse(new String[]{});
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUris).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
