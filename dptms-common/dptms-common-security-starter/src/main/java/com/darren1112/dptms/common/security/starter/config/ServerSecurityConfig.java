package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.core.constants.FilterOrderConstant;
import com.darren1112.dptms.common.security.starter.filter.ZuulHeaderValidFilter;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 服务权限自动配置
 *
 * @author luyuhao
 * @since 2020/08/04 01:35
 */
public class ServerSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public FilterRegistrationBean zuulHeaderValidFilterBean() {
        ZuulHeaderValidFilter zuulHeaderValidFilter = new ZuulHeaderValidFilter(securityProperties);
        FilterRegistrationBean<ZuulHeaderValidFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(zuulHeaderValidFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(FilterOrderConstant.ZUUL_HEADER_VALID_FILTER);
        filterRegistrationBean.setName("zuulHeaderValidFilter");
        return filterRegistrationBean;
    }
}
