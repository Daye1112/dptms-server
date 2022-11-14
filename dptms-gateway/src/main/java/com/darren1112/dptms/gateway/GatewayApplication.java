package com.darren1112.dptms.gateway;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.sdk.starter.doc.annotation.EnableGatewayDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 *
 * @author luyuhao
 * @since 2020/07/31 02:12
 */
@EnableZuulProxy
@EnableGatewayDoc
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(GatewayApplication.class, args);
    }
}
