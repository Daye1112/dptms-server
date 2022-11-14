package com.darren1112.dptms.sdk.starter.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网关配置
 *
 * @author luyuhao
 * @since 2020/08/02 18:22
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

    /**
     * 免认证资源路径，支持通配符
     * 多个值时使用逗号分隔
     */
    private String[] anonUris;

    /**
     * 免权限认证资源路径，支持通配符
     * 多个值时使用逗号分隔
     */
    private String[] anonPermissionUris;

    /**
     * token有效期(s)
     */
    private int accessTokenExpired = 60 * 15;

    /**
     * 刷新token有效期(s)
     */
    private int refreshTokenExpired = 60 * 60 * 24 * 7;
}
