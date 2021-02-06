package com.darren1112.dptms.common.log.starter.properties;

import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志采集配置
 *
 * @author luyuhao
 * @date 2021/02/07 01:42
 */
@Data
@ConfigurationProperties(prefix = "dptms.log")
public class LogProperties {

    /**
     * 日志级别
     */
    private LogLevel logLevel = LogLevel.INFO;

    /**
     * 日志采集线程池池最大线程数
     */
    private int logMaxPoolSize = 200;

    /**
     * 日志采集线程池最大维护数
     */
    private int logCorePoolSize = 20;
}
