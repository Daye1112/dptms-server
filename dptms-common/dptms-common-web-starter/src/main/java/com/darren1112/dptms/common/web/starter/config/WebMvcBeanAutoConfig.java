package com.darren1112.dptms.common.web.starter.config;

import com.darren1112.dptms.common.web.starter.exception.GlobalExceptionHandler;
import com.darren1112.dptms.common.web.starter.exception.GlobalFeignErrorHandler;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * mvc的bean管理配置类
 *
 * @author luyuhao
 * @date 2020/1/6 18:41
 */
public class WebMvcBeanAutoConfig {

    @Bean
    public DefaultWebMvcAutoConfig defaultWebMvcAutoConfig() {
        return new DefaultWebMvcAutoConfig();
    }

    /**
     * feign全局异常配置
     *
     * @return 异常信息
     * @author luyuhao
     * @date 20/08/02 21:57
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new GlobalFeignErrorHandler();
    }

    @Bean
    public GlobalExceptionHandler defaultGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
