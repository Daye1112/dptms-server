package com.darren1112.dptms.common.fastdfs.starter.config;

import com.darren1112.dptms.common.fastdfs.starter.core.factory.FileHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.core.factory.handle.FileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.factory.handle.LargeFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.factory.handle.SmallFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.fastdfs.starter.core.client.FileClient;
import com.darren1112.dptms.common.fastdfs.starter.core.client.impl.FastDfsFileClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * FastDfs自动装配类
 *
 * @author luyuhao
 * @date 2021/11/28 21:12
 */
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsAutoConfig {

    @Autowired
    private FastDfsProperties fastDfsProperties;

    @Bean
    public FileClient fileClient(FastFileStorageClient fastFileStorageClient, FileHandlerFactory fileHandlerFactory) {
        return new FastDfsFileClient(fastFileStorageClient, fastDfsProperties, fileHandlerFactory);
    }

    @Bean("smallFileHandler")
    public FileHandler smallFileHandler(FastFileStorageClient fastFileStorageClient) {
        return new SmallFileHandler(fastFileStorageClient);
    }

    @Bean("largeFileHandler")
    public FileHandler largeFileHandler(FastFileStorageClient fastFileStorageClient) {
        return new LargeFileHandler(fastFileStorageClient, fastDfsProperties);
    }

    @Bean
    public FileHandlerFactory fileHandlerFactory(FileHandler smallFileHandler, FileHandler largeFileHandler) {
        return new FileHandlerFactory(largeFileHandler, smallFileHandler, fastDfsProperties);
    }
}
