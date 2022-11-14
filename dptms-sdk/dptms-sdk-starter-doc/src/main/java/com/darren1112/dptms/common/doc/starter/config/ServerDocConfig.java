package com.darren1112.dptms.common.doc.starter.config;

import com.darren1112.dptms.common.doc.starter.properties.ServerDocProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 文档自动配置类
 *
 * @author luyuhao
 * @since 2020/08/06 02:36
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties(ServerDocProperties.class)
public class ServerDocConfig {

    @Autowired
    private ServerDocProperties serverDocProperties;

    @Bean
    @Order(-1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
        // .securityContexts(Lists.newArrayList(securityContext()))
        // .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }


    private ApiInfo groupApiInfo() {
        Contact contact = new Contact(serverDocProperties.getName(), "", serverDocProperties.getEmail());

        return new ApiInfoBuilder()
                .title(serverDocProperties.getTitle())
                .description(serverDocProperties.getDescription())
                .contact(contact)
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .version(serverDocProperties.getVersion())
                .build();
    }


    // private ApiKey apiKey() {
    //     return new ApiKey("AccessToken", "Authorization", "header");
    // }
    //
    // private SecurityContext securityContext() {
    //     return SecurityContext.builder()
    //             .securityReferences(defaultAuth())
    //             .forPaths(PathSelectors.regex("/.*"))
    //             .build();
    // }
    //
    // private List<SecurityReference> defaultAuth() {
    //     AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    //     AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    //     authorizationScopes[0] = authorizationScope;
    //     return Lists.newArrayList(new SecurityReference("AccessToken", authorizationScopes));
    // }

}
