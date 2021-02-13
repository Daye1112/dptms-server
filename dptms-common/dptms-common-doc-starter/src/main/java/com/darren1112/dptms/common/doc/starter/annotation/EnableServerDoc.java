package com.darren1112.dptms.common.doc.starter.annotation;

import com.darren1112.dptms.common.doc.starter.config.ServerDocConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启服务接口文档注解
 *
 * @author luyuhao
 * @date 2021/02/13 22:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerDocConfig.class)
public @interface EnableServerDoc {
}
