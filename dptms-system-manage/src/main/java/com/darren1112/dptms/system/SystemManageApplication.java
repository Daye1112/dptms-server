package com.darren1112.dptms.system;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.sdk.starter.doc.annotation.EnableServerDoc;
import com.darren1112.dptms.sdk.starter.security.annotation.EnableServerSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luyuhao
 * @since 2020/07/25 01:34
 */
@EnableServerDoc
@EnableEurekaClient
@EnableServerSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class})
public class SystemManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(SystemManageApplication.class, args);
    }
}
