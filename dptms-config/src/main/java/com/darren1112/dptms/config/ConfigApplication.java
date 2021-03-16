package com.darren1112.dptms.config;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luyuhao
 * @since 2020/07/19 22:09
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        System.setProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY, "native");
        SpringApplication.run(ConfigApplication.class, args);
    }
}
