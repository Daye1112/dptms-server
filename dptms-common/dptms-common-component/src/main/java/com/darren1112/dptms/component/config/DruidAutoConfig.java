package com.darren1112.dptms.component.config;

import com.darren1112.dptms.component.controller.DruidStatController;
import com.darren1112.dptms.component.service.DruidService;
import com.darren1112.dptms.component.service.impl.DruidServiceImpl;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * druid组件配置
 *
 * @author luyuhao
 * @date 2021/02/15 21:55
 */
@EnableFeignClients(basePackages = {"com.darren1112.dptms"})
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
