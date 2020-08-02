package com.darren1112.dptms.config;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author luyuhao
 * @date 2020/07/19 22:09
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        System.setProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY, "native");
        SpringApplication.run(ConfigApplication.class, args);
    }
}
