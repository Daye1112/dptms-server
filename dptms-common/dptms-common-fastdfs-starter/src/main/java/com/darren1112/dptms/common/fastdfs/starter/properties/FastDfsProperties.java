package com.darren1112.dptms.common.fastdfs.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FastDfs相关配置
 *
 * @author luyuhao
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
     * 大文件切割大小，默认20MB
     */
    private int splitSize = 20 * 1024 * 1024;
}
