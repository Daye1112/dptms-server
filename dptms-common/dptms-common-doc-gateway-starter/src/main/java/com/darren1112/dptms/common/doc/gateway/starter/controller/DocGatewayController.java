package com.darren1112.dptms.common.doc.gateway.starter.controller;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.doc.gateway.starter.properties.DocGatewayProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 网关文档Controller
 *
 * @author luyuhao
 * @since 2020/8/7 14:18
 */
@RestController
@AllArgsConstructor
public class DocGatewayController {

    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;
    private SwaggerResourcesProvider swaggerResources;
    private DocGatewayProperties docGatewayProperties;

    @GetMapping("/swagger-resources/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(ResponseEntityUtil.ok(Optional.ofNullable(securityConfiguration)
                .orElse(SecurityConfigurationBuilder.builder().build())));
    }

    @GetMapping("/swagger-resources/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(ResponseEntityUtil.ok(Optional.ofNullable(uiConfiguration)
                .orElse(UiConfigurationBuilder.builder().build())));
    }

    @GetMapping("/swagger-resources")
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources() {
        List<SwaggerResource> swaggerResources = this.swaggerResources.get();
        List<SwaggerResource> filterList = new ArrayList<>();
        String[] resources = docGatewayProperties.getResources();
        if (CollectionUtil.isNotEmpty(resources)) {
            List<String> resourceList = Arrays.asList(resources);
            swaggerResources.stream()
                    .filter(item -> resourceList.contains(item.getName()))
                    .forEach(filterList::add);
            return Mono.just(ResponseEntityUtil.ok(filterList));
        }
        return Mono.just(ResponseEntityUtil.ok(swaggerResources));
    }
}
