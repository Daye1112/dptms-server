package com.darren1112.dptms.sdk.starter.security.config;

import com.darren1112.dptms.common.core.constants.FilterOrderConstant;
import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.BasicAuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.token.generator.UuidTokenGenerator;
import com.darren1112.dptms.sdk.starter.security.core.token.generator.base.TokenGenerator;
import com.darren1112.dptms.sdk.starter.security.core.token.manager.TokenValidatorManager;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.BasicTokenValidator;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.RepeatTokenValidator;
import com.darren1112.dptms.sdk.starter.security.filter.SecurityUserFilter;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Base64Utils;

/**
 * 安全验证自动配置
 *
 * @author darren
 * @since 2020/12/07 00:54
 */
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public RequestInterceptor feignRequestInterceptor(TokenStore tokenStore) {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
            requestTemplate.header(securityProperties.getHeaderKey(), gatewayToken);
            requestTemplate.header(SecurityConstant.REFRESH_TOKEN_KEY, tokenStore.getRefreshToken());
            requestTemplate.header(SecurityConstant.ACCESS_TOKEN_KEY, tokenStore.getAccessToken());
        };
    }

    @Bean
    @ConditionalOnMissingBean(TokenGenerator.class)
    public TokenGenerator tokenGenerator() {
        return new UuidTokenGenerator();
    }

    @Bean
    public TokenStore tokenStore(RedisUtil redisUtil, TokenGenerator tokenGenerator) {
        return new TokenStore(redisUtil, securityProperties, tokenGenerator, ActiveUser.class);
    }

    @Bean
    public BasicTokenValidator basicTokenValidator(TokenStore tokenStore) {
        return new BasicTokenValidator(tokenStore);
    }

    @Bean
    public RepeatTokenValidator repeatTokenValidator(TokenStore tokenStore) {
        return new RepeatTokenValidator(tokenStore);
    }

    @Lazy
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public TokenValidatorManager tokenValidatorManager() {
        return new TokenValidatorManager();
    }

    @Bean
    @ConditionalOnMissingBean(AuthTypeFactory.class)
    public AuthTypeFactory authTypeFactory() {
        return new BasicAuthTypeFactory();
    }

    @Bean
    public FilterRegistrationBean securityUserFilter(TokenStore tokenStore, AuthTypeFactory authTypeFactory) {
        SecurityUserFilter securityUserFilter = new SecurityUserFilter(tokenStore, authTypeFactory);
        FilterRegistrationBean<SecurityUserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(securityUserFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        //order的数值越小 则优先级越高
        filterRegistrationBean.setOrder(FilterOrderConstant.SECURITY_USER_FILTER);
        filterRegistrationBean.setName("securityUserFilter");
        return filterRegistrationBean;
    }
}
