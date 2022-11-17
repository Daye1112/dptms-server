package com.darren1112.dptms.auth.common.config;

import com.darren1112.dptms.auth.common.security.auth.DptmsAuthenticationFailureHandler;
import com.darren1112.dptms.auth.common.security.auth.DptmsAuthenticationSuccessHandler;
import com.darren1112.dptms.auth.common.security.auth.DptmsLogoutSuccessHandler;
import com.darren1112.dptms.auth.common.security.processing.filter.DptmsAuthenticationProcessingFilter;
import com.darren1112.dptms.auth.common.security.processing.provider.PwdAuthenticationProvider;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * web端security配置类
 *
 * @author luyuhao
 * @since 2022/11/15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PwdAuthenticationProvider pwdAuthenticationProvider;

    @Autowired
    private DptmsAuthenticationSuccessHandler dptmsAuthenticationSuccessHandler;

    @Autowired
    private DptmsAuthenticationFailureHandler dptmsAuthenticationFailureHandler;

    @Autowired
    private DptmsLogoutSuccessHandler dptmsLogoutSuccessHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthTypeFactory authTypeFactory;

    @Bean
    public DptmsAuthenticationProcessingFilter dptmsAuthenticationProcessingFilter() {
        DptmsAuthenticationProcessingFilter filter = new DptmsAuthenticationProcessingFilter(securityProperties, authenticationManager, authTypeFactory);
        filter.setAuthenticationSuccessHandler(dptmsAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(dptmsAuthenticationFailureHandler);
        return filter;
    }

    /**
     * 认证校验器配置
     *
     * @param auth 认证器管理中心
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 将认证器注册到管理中心中
        auth.authenticationProvider(pwdAuthenticationProvider);
    }

    /**
     * 安全相关配置
     *
     * @param http http安全类
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF
                .csrf().disable()

                // 登录配置
                .formLogin()
                .permitAll()
                .loginProcessingUrl(securityProperties.getLoginPath())
                .successHandler(dptmsAuthenticationSuccessHandler)
                .failureHandler(dptmsAuthenticationFailureHandler)

                // 登出配置
                .and()
                .logout()
                .logoutUrl(securityProperties.getLogoutPath())
                .permitAll()
                .logoutSuccessHandler(dptmsLogoutSuccessHandler)

                // 过滤请求
                .and()
                .authorizeRequests()
                // 放行所有请求, 走自定义拦截
                .anyRequest().permitAll()


                .and()
                // 防止iframe 造成跨域
                .headers()
                .frameOptions().disable()

                // 会话管理
                .and()
                .sessionManagement()
                // 不创建会话
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


                // 授权异常捕获
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.addFilterBefore(dptmsAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
