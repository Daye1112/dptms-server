package com.darren1112.dptms.file;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.sdk.starter.doc.annotation.EnableServerDoc;
import com.darren1112.dptms.sdk.starter.security.annotation.EnableServerSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 文件管理启动类
 *
 * @author luyuhao
 * @since 2021/11/28
 */
@EnableServerDoc
@EnableEurekaClient
@EnableServerSecurity
@SpringBootApplication
public class FileManageApplication {

    public static void main(String[] args) {
        EnvironmentAwareUtil.adjust();
        SpringApplication.run(FileManageApplication.class, args);
    }
}
