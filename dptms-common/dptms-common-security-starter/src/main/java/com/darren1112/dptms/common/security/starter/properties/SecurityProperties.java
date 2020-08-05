package com.darren1112.dptms.common.security.starter.properties;

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
@ConfigurationProperties(prefix = "dptms.security")
public class SecurityProperties {

    /**
     * zuul的headers传递的key
     */
    private String headerKey;

    /**
     * zuul的headers传递的value
     */
    private String headerValue;

}
