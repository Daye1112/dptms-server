package com.darren1112.dptms.sdk.starter.fastdfs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FastDfs相关配置
 *
 * @author darren
 * @date 2021/11/28 16:22
 */
@Data
@ConfigurationProperties(prefix = "dptms.fastdfs")
public class FastDfsProperties {

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 大文件切割大小，默认50MB
     */
    private int splitSize = 50 * 1024 * 1024;

    /**
     * 重试次数，<2表示不启用重试机制
     */
    private int retryTimes = 3;

    /**
     * 文件处理线程池池最大线程数
     */
    private int fileMaxPoolSize = 200;

    /**
     * 文件处理线程池最大维护数
     */
    private int fileCorePoolSize = 20;
}
