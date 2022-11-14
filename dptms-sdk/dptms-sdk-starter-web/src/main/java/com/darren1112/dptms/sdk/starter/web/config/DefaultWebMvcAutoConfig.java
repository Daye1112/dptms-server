package com.darren1112.dptms.sdk.starter.web.config;

import com.darren1112.dptms.sdk.starter.web.convert.String2DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 *
 * @author luyuhao
 * @since 2019/12/6 9:32
 */
@Slf4j
public class DefaultWebMvcAutoConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(0);
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
    }
}
