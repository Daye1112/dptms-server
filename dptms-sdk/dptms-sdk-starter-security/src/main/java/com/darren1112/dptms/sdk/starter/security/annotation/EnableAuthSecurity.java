package com.darren1112.dptms.sdk.starter.security.annotation;

import com.darren1112.dptms.sdk.starter.security.config.AuthSecurityConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动认证服务配置
 *
 * @author luyuhao
 * @since 2020/12/06 23:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthSecurityConfig.class)
public @interface EnableAuthSecurity {
}
