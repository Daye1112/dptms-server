package com.darren1112.dptms.sdk.starter.security.config;

import com.darren1112.dptms.common.core.constants.FilterOrderConstant;
import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.security.core.DptmsTokenStore;
import com.darren1112.dptms.sdk.starter.security.core.DptmsTokenValidator;
import com.darren1112.dptms.sdk.starter.security.filter.AddCommonInfoFilter;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
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
 * @since 2020/12/07 00:54
 */
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public RequestInterceptor feignRequestInterceptor(DptmsTokenStore dptmsTokenStore) {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
            requestTemplate.header(securityProperties.getHeaderKey(), gatewayToken);
            requestTemplate.header(SecurityConstant.REFRESH_TOKEN_KEY, dptmsTokenStore.getRefreshToken());
            requestTemplate.header(SecurityConstant.ACCESS_TOKEN_KEY, dptmsTokenStore.getAccessToken());
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
    public FilterRegistrationBean addCommonInfoFilter(DptmsTokenStore dptmsTokenStore) {
        AddCommonInfoFilter addCommonInfoFilter = new AddCommonInfoFilter(dptmsTokenStore);
        FilterRegistrationBean<AddCommonInfoFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(addCommonInfoFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(FilterOrderConstant.ADD_USER_FILTER);
        filterRegistrationBean.setName("addCommonInfoFilter");
        return filterRegistrationBean;
    }
}
