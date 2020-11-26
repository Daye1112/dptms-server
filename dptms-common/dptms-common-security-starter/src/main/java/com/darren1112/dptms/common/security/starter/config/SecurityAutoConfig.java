package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.security.starter.filter.ZuulHeaderValidFilter;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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
    public ZuulHeaderValidFilter zuulHeaderValidFilter() {
        return new ZuulHeaderValidFilter(securityProperties);
    }

    @Bean
    public FilterRegistrationBean zuulHeaderValidFilterBean() {
        FilterRegistrationBean<ZuulHeaderValidFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(zuulHeaderValidFilter());
        filterRegistrationBean.addUrlPatterns("/**");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.setName("zuulHeaderValidFilter");
        return filterRegistrationBean;
    }
}
