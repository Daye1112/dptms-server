package com.darren1112.dptms.sdk.starter.doc.config;

import com.darren1112.dptms.sdk.starter.doc.controller.GatewayDocController;
import com.darren1112.dptms.sdk.starter.doc.properties.GatewayDocProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * 网关文档配置类
 *
 * @author darren
 * @since 2020/08/07 01:56
 */
@EnableConfigurationProperties(GatewayDocProperties.class)
public class GatewayDocConfig {

    @Autowired
    private GatewayDocProperties gatewayDocProperties;

    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @Bean
    public GatewayDocResourceConfig gatewayDocResourceConfig(RouteLocator routeLocator) {
        return new GatewayDocResourceConfig(routeLocator, gatewayDocProperties);
    }

    @Bean
    public GatewayDocController gatewayDocController(SwaggerResourcesProvider swaggerResources) {
        return new GatewayDocController(securityConfiguration, uiConfiguration,
                swaggerResources, gatewayDocProperties);
    }
}
