package com.darren1112.dptms.common.security.starter.annotation;

import com.darren1112.dptms.common.security.starter.config.SecurityResourceServerAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动资源服务器配置注解
 *
 * @author luyuhao
 * @date 2020/08/05 02:18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SecurityResourceServerAutoConfig.class)
public @interface EnableSecurityResourceServer {
}
