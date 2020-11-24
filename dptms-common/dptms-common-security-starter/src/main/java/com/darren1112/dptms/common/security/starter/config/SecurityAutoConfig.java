package com.darren1112.dptms.common.security.starter.config;

import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
    public TokenUtil tokenUtil(RedisUtil redisUtil) {
        return new TokenUtil(redisUtil);
    }

}
