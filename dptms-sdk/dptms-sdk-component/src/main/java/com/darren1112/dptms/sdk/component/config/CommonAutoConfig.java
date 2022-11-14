package com.darren1112.dptms.sdk.component.config;

import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 通用组件配置
 *
 * @author luyuhao
 * @since 2021/3/6 15:37
 */
@EnableFeignClients(basePackages = {"com.darren1112.dptms"})
public class CommonAutoConfig {
}
