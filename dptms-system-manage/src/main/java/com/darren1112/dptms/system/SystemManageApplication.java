package com.darren1112.dptms.system;

import com.darren1112.dptms.common.util.EnvironmentAwareUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luyuhao
 * @date 2020/07/25 01:34
 */
@EnableCaching
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.darren1112.dptms.system",
        "com.darren1112.dptms.common.component"})
public class SystemManageApplication {

    public static void main(String[] args){
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(SystemManageApplication.class, args);
    }
}
