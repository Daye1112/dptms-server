package com.darren1112.dptms.gateway.common.config;

import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.gateway.common.filter.PermissionValidateFilter;
import com.darren1112.dptms.gateway.common.filter.TokenValidateFilter;
import com.darren1112.dptms.gateway.remoting.AuthRemoting;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * zuul配置类
 *
 * @author luyuhao
 * @date 2020/08/02 21:59
 */
@Configuration
public class ZuulConfig {

    /**
     * 跨域处理
     *
     * @return CorsFilter
     * @author luyuhao
     * @date 20/08/02 22:02
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        //允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedOrigin(CorsConfiguration.ALL);
        //允许访问的头信息,*表示全部
        config.addAllowedHeader(CorsConfiguration.ALL);
        //允许提交请求的方法，*表示全部允许
        config.addAllowedMethod(CorsConfiguration.ALL);
        //预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(7200L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean tokenValidateFilterBean(TokenUtil tokenUtil, SecurityProperties securityProperties, AuthRemoting authRemoting) {
        TokenValidateFilter tokenValidateFilter = new TokenValidateFilter(tokenUtil, securityProperties, authRemoting);
        FilterRegistrationBean<TokenValidateFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(tokenValidateFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.setName("tokenValidateFilter");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean permissionValidateFilterBean(TokenUtil tokenUtil, SecurityProperties securityProperties) {
        PermissionValidateFilter permissionValidateFilter = new PermissionValidateFilter(securityProperties, tokenUtil);
        FilterRegistrationBean<PermissionValidateFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(permissionValidateFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setName("permissionValidateFilter");
        return filterRegistrationBean;
    }
}
