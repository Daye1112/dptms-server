package com.darren1112.dptms.common.component.config;

import com.darren1112.dptms.common.component.exception.GlobalFeignErrorDecoder;
import com.darren1112.dptms.common.component.properties.GatewayProperties;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * feign配置
 *
 * @author luyuhao
 * @date 2020/08/02 19:24
 */
@Configuration
public class FeignConfig {

    @Autowired
    private GatewayProperties gatewayProperties;

    /**
     * feign全局异常配置
     *
     * @return 异常信息
     * @author luyuhao
     * @date 20/08/02 21:57
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new GlobalFeignErrorDecoder();
    }

    /**
     * feign请求统一携带请求头
     *
     * @return 拦截器
     * @author luyuhao
     * @date 20/08/02 21:58
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(gatewayProperties.getZuulTokenValue().getBytes()));
            requestTemplate.header(gatewayProperties.getZuulTokenKey(), zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}
