package com.darren1112.dptms.sdk.starter.doc.controller;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.sdk.starter.doc.properties.GatewayDocProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.ArrayList;
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
public class GatewayDocController {

    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;
    private SwaggerResourcesProvider swaggerResources;
    private GatewayDocProperties gatewayDocProperties;

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
        String[] unResources = gatewayDocProperties.getUnResources();
        if (CollectionUtil.isNotEmpty(unResources)) {
            List<String> resourceList = CollectionUtil.arrayToList(unResources);
            swaggerResources.stream()
                    .filter(item -> !resourceList.contains(item.getName()))
                    .forEach(filterList::add);
            return Mono.just(ResponseEntityUtil.ok(filterList));
        }
        return Mono.just(ResponseEntityUtil.ok(swaggerResources));
    }
}
