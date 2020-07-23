package com.darren1112.dptms.auth;

import com.darren1112.dptms.common.util.EnvironmentAwareUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luyuhao
 * @date 2020/07/18 22:19
 */
@EnableCaching
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.darren1112.dptms.auth",
        "com.darren1112.dptms.common.component"})
public class AuthApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(AuthApplication.class, args);
    }
}
