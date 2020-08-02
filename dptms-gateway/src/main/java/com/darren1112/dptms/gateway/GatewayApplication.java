package com.darren1112.dptms.gateway;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 *
 * @author luyuhao
 * @date 2020/07/31 02:12
 */
@EnableZuulProxy
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.darren1112.dptms.gateway",
        "com.darren1112.dptms.common.component"})
public class GatewayApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(GatewayApplication.class, args);
    }
}
