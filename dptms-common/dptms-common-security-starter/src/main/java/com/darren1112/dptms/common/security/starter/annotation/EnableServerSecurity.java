package com.darren1112.dptms.common.security.starter.annotation;

import com.darren1112.dptms.common.security.starter.config.ServerSecurityConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动服务权限验证
 *
 * @author luyuhao
 * @since 2020/12/06 23:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerSecurityConfig.class)
public @interface EnableServerSecurity {
}
