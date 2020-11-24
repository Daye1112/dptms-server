package com.darren1112.dptms.auth.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 认证服务配置类型
 *
 * @author luyuhao
 * @date 2020/08/01 03:36
 */
@Data
@Component
@SpringBootConfiguration
@ConfigurationProperties(prefix = "dptms.auth")
public class AuthProperties {

    private long accessTokenExpired = 60 * 15;
    private long refreshTokenExpired = 60 * 60 * 24 * 7;
}
