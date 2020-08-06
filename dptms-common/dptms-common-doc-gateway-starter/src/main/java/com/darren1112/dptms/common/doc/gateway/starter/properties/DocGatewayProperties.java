package com.darren1112.dptms.common.doc.gateway.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网关文档配置类
 *
 * @author luyuhao
 * @date 2020/08/07 01:57
 */
@Data
@ConfigurationProperties(prefix = "dptms.doc.gateway")
public class DocGatewayProperties {

    /**
     * 是否开启网关文档聚合功能
     */
    private Boolean enable;

    /**
     * 需要暴露doc的服务列表，多个值时用逗号分隔
     * 如，dptms-auth, dptms-system-manage
     */
    private String[] resources;
}
