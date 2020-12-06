package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
    public TokenUtil tokenUtil(RedisUtil redisUtil) {
        return new TokenUtil(redisUtil);
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
            requestTemplate.header(securityProperties.getHeaderKey(), gatewayToken);
        };
    }
}
