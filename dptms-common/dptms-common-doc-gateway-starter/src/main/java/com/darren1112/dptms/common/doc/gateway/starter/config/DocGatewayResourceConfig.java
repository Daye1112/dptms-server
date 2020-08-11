package com.darren1112.dptms.common.doc.gateway.starter.config;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.doc.gateway.starter.properties.DocGatewayProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关资源配置
 *
 * @author luyuhao
 * @date 2020/08/07 02:00
 */
@Slf4j
@Primary
@AllArgsConstructor
public class DocGatewayResourceConfig implements SwaggerResourcesProvider {

    private RouteLocator routeLocator;

    private DocGatewayProperties docGatewayProperties;

    private final static String SUFFIX = "v2/api-docs";

    @Override
    public List<SwaggerResource> get() {
        //获取所有router
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        String[] unAccessResource = docGatewayProperties.getUnResources();
        List<String> unAccessResourceList = CollectionUtil.arrayToList(unAccessResource);
        if (CollectionUtil.isNotEmpty(routes)) {
            routes.stream().filter(item -> !unAccessResourceList.contains(item.getId()))
                    .forEach(item -> resources.add(swaggerResource(item.getId(),
                            item.getFullPath().replace("**", SUFFIX))));
        }
        log.info("Doc Resources Size:{}", resources.size());
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("serviceId:{}，location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }


}
