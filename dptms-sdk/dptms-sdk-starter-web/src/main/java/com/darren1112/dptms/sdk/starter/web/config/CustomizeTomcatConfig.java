package com.darren1112.dptms.sdk.starter.web.config;

import com.darren1112.dptms.sdk.starter.web.properties.TomcatConnectorProperties;
import com.darren1112.dptms.sdk.starter.web.tomcat.DptmsTomcatConnectorCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * tomcat配置
 *
 * @author luyuhao
 * @since 2021/5/10 9:13
 */
@Slf4j
@EnableConfigurationProperties(TomcatConnectorProperties.class)
public class CustomizeTomcatConfig {

    @Autowired
    private TomcatConnectorProperties tomcatConnectorProperties;

    @Bean
    public DptmsTomcatConnectorCustomizer dptmsTomcatConnectorCustomizer() {
        return new DptmsTomcatConnectorCustomizer(tomcatConnectorProperties);
    }

}
