package com.darren1112.dptms.sdk.starter.doc.annotation;

import com.darren1112.dptms.sdk.starter.doc.config.GatewayDocConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 是否开启网关接口文档注解
 *
 * @author luyuhao
 * @since 2021/02/13 22:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GatewayDocConfig.class)
public @interface EnableGatewayDoc {
}
