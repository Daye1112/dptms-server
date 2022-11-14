package com.darren1112.dptms.sdk.component.config;

import com.darren1112.dptms.sdk.component.controller.DruidStatController;
import com.darren1112.dptms.sdk.component.service.DruidService;
import com.darren1112.dptms.sdk.component.service.impl.DruidServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * druid组件配置
 *
 * @author luyuhao
 * @since 2021/02/15 21:55
 */
public class DruidAutoConfig {

    @Bean
    public DruidService druidService() {
        return new DruidServiceImpl();
    }

    @Bean
    public DruidStatController druidStatController(DruidService druidService) {
        return new DruidStatController(druidService);
    }
}
