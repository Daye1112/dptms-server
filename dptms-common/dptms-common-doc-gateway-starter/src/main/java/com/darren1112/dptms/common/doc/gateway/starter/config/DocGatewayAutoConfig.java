package com.darren1112.dptms.common.doc.gateway.starter.config;

import com.darren1112.dptms.common.doc.gateway.starter.controller.DocGatewayController;
import com.darren1112.dptms.common.doc.gateway.starter.properties.DocGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * 网关文档配置类
 *
 * @author luyuhao
 * @date 2020/08/07 01:56
 */
@EnableConfigurationProperties(DocGatewayProperties.class)
@ConditionalOnProperty(value = "dptms.doc.gateway.enable", havingValue = "true", matchIfMissing = true)
public class DocGatewayAutoConfig {

    @Autowired
    private DocGatewayProperties docGatewayProperties;

    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @Autowired
    private RouteLocator routeLocator;

    @Bean
    public DocGatewayResourceConfig docGatewayResourceConfig() {
        return new DocGatewayResourceConfig(routeLocator, docGatewayProperties);
    }

    @Bean
    public DocGatewayController docGatewayController(SwaggerResourcesProvider swaggerResources) {
        return new DocGatewayController(securityConfiguration, uiConfiguration,
                swaggerResources, docGatewayProperties);
    }
}
