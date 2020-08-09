package com.darren1112.dptms.system;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.common.security.starter.annotation.EnableSecurityResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author luyuhao
 * @date 2020/07/25 01:34
 */
@EnableCaching
@EnableSecurityResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class SystemManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(SystemManageApplication.class, args);
    }
}
