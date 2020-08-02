package com.darren1112.dptms.common.component.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 网关配置
 *
 * @author luyuhao
 * @date 2020/08/02 18:22
 */
@Data
@Component
@SpringBootConfiguration
@ConfigurationProperties(prefix = "dptms.gateway")
public class GatewayProperties {

    /**
     * zuul的headers传递的key
     */
    private String zuulTokenKey;

    /**
     * zuul的headers传递的value
     */
    private String zuulTokenValue;

}
