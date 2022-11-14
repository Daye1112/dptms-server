package com.darren1112.dptms.sdk.starter.doc.annotation;

import com.darren1112.dptms.sdk.starter.doc.config.ServerDocConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启服务接口文档注解
 *
 * @author luyuhao
 * @since 2021/02/13 22:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerDocConfig.class)
public @interface EnableServerDoc {
}
