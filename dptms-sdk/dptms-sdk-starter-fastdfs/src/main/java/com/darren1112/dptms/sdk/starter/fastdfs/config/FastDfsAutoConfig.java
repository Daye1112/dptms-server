package com.darren1112.dptms.sdk.starter.fastdfs.config;

import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.handle.FastDfsHandler;
import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.handle.RetryFastDfsHandler;
import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.handle.SimpleFastDfsHandler;
import com.darren1112.dptms.sdk.starter.fastdfs.properties.FastDfsProperties;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * FastDfs自动装配类
 *
 * @author darren
 * @date 2021/11/28 21:12
 */
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsAutoConfig {

    @Autowired
    private FastDfsProperties fastDfsProperties;

    @Bean("retryFastDfsHandler")
    public FastDfsHandler retryFastDfsHandler(FastFileStorageClient fastFileStorageClient) {
        return new RetryFastDfsHandler(fastFileStorageClient, fastDfsProperties);
    }

    @Bean("simpleFastDfsHandler")
    public FastDfsHandler simpleFastDfsHandler(FastFileStorageClient fastFileStorageClient) {
        return new SimpleFastDfsHandler(fastFileStorageClient);
    }

    @Bean
    public FastDfsHandlerFactory fastDfsHandlerFactory(FastDfsHandler retryFastDfsHandler,
                                                       FastDfsHandler simpleFastDfsHandler) {
        return new FastDfsHandlerFactory(fastDfsProperties, retryFastDfsHandler, simpleFastDfsHandler);
    }
}
