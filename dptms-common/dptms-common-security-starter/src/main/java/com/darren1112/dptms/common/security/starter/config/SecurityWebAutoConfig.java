package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.security.starter.interceptor.ZuulHeaderValidInterceptor;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 *
 * @author luyuhao
 * @date 2019/12/6 9:32
 */
@Slf4j
public class SecurityWebAutoConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public ZuulHeaderValidInterceptor zuulHeaderValidInterceptor(){
        return new ZuulHeaderValidInterceptor(securityProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(zuulHeaderValidInterceptor());
    }
}
