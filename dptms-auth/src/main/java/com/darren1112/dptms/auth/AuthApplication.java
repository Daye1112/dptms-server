package com.darren1112.dptms.auth;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.sdk.starter.doc.annotation.EnableServerDoc;
import com.darren1112.dptms.sdk.starter.security.annotation.EnableAuthSecurity;
import com.darren1112.dptms.sdk.starter.security.annotation.EnableServerSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luyuhao
 * @since 2020/07/18 22:19
 */
@EnableServerDoc
@EnableEurekaClient
@EnableAuthSecurity
@EnableServerSecurity
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(AuthApplication.class, args);
    }
}
