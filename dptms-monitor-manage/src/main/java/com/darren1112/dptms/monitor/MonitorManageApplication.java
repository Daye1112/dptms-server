package com.darren1112.dptms.monitor;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.common.doc.starter.annotation.EnableServerDoc;
import com.darren1112.dptms.common.security.starter.annotation.EnableServerSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 监控服务启动类
 *
 * @author luyuhao
 * @since 2020/08/09 21:05
 */
@EnableServerDoc
@EnableEurekaClient
@EnableServerSecurity
@SpringBootApplication
public class MonitorManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(MonitorManageApplication.class, args);
    }

}
