package com.darren1112.dptms.common.fastdfs.starter.config;

import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.fastdfs.starter.service.FileService;
import com.darren1112.dptms.common.fastdfs.starter.service.impl.FileServiceImpl;
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
    public FileService fileService(FastFileStorageClient fastFileStorageClient) {
        return new FileServiceImpl(fastFileStorageClient, fastDfsProperties);
    }
}
