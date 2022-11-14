package com.darren1112.dptms.sdk.starter.doc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网关文档配置类
 *
 * @author luyuhao
 * @since 2020/08/07 01:57
 */
@Data
@ConfigurationProperties(prefix = "dptms.doc.gateway")
public class GatewayDocProperties {

    /**
     * 不需要暴露doc的服务列表，多个值时用逗号分隔
     * 如，dptms-auth, dptms-system-manage
     */
    private String[] unResources;
}
