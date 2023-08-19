package com.darren1112.dptms.monitor;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.sdk.starter.doc.annotation.EnableServerDoc;
import com.darren1112.dptms.sdk.starter.security.annotation.EnableServerSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 监控服务启动类
 *
 * @author darren
 * @since 2020/08/09 21:05
 */
@EnableServerDoc
@EnableEurekaClient
@EnableServerSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class})
public class MonitorManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(MonitorManageApplication.class, args);
    }

}
