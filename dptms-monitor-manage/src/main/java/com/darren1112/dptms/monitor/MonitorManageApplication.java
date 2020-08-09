package com.darren1112.dptms.monitor;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 监控服务启动类
 *
 * @author luyuhao
 * @date 2020/08/09 21:05
 */
@EnableAdminServer
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class MonitorManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(MonitorManageApplication.class, args);
    }

}
