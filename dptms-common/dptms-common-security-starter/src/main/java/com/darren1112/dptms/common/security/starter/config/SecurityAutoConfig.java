package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.core.constants.FilterOrderConstant;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenValidator;
import com.darren1112.dptms.common.security.starter.filter.AddUserFilter;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Base64Utils;

/**
 * 安全验证自动配置
 *
 * @author luyuhao
 * @date 2020/12/07 00:54
 */
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
            requestTemplate.header(securityProperties.getHeaderKey(), gatewayToken);
        };
    }

    @Bean
    public DptmsTokenStore dptmsTokenStore(RedisUtil redisUtil) {
        return new DptmsTokenStore(redisUtil, securityProperties);
    }

    @Bean
    public DptmsTokenValidator dptmsTokenValidator(DptmsTokenStore dptmsTokenStore) {
        return new DptmsTokenValidator(dptmsTokenStore);
    }

    @Bean
    public FilterRegistrationBean addUserFilter(DptmsTokenStore dptmsTokenStore) {
        AddUserFilter addUserFilter = new AddUserFilter(dptmsTokenStore);
        FilterRegistrationBean<AddUserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(addUserFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(FilterOrderConstant.ADD_USER_FILTER);
        filterRegistrationBean.setName("zuulHeaderValidFilter");
        return filterRegistrationBean;
    }
}
