package com.darren1112.dptms.common.fastdfs.starter.config;

import com.darren1112.dptms.common.core.thread.decorator.ThreadPoolDecorator;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.FileClient;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.impl.CommonFileClient;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.FileHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.FileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.LargeFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.SmallFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

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

    /**
     * 文件处理线程池配置
     *
     * @return {@link ThreadPoolTaskExecutor}
     * @author luyuhao
     * @since 2021/12/7
     */
    @Bean
    public ThreadPoolTaskExecutor fileHandleThreadPool() {
        ThreadPoolTaskExecutor fileHandleThreadPool = new ThreadPoolTaskExecutor();
        fileHandleThreadPool.setMaxPoolSize(fastDfsProperties.getFileMaxPoolSize());
        fileHandleThreadPool.setCorePoolSize(fastDfsProperties.getFileCorePoolSize());
        fileHandleThreadPool.setThreadNamePrefix("fileHandleExecutor-");
        // 传递上下文
        fileHandleThreadPool.setTaskDecorator(new ThreadPoolDecorator());
        // 当线程池已满,且等待队列也满了的时候,转为主线程执行
        fileHandleThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        fileHandleThreadPool.initialize();
        return fileHandleThreadPool;
    }

    @Bean
    public FileClient fileClient(FastFileStorageClient fastFileStorageClient, FileHandlerFactory fileHandlerFactory) {
        return new CommonFileClient(fastFileStorageClient, fastDfsProperties, fileHandlerFactory);
    }

    @Bean("smallFileHandler")
    public FileHandler smallFileHandler(FastFileStorageClient fastFileStorageClient) {
        return new SmallFileHandler(fastFileStorageClient);
    }

    @Bean("largeFileHandler")
    public FileHandler largeFileHandler(FastFileStorageClient fastFileStorageClient, ThreadPoolTaskExecutor fileHandleThreadPool) {
        return new LargeFileHandler(fastFileStorageClient, fastDfsProperties, fileHandleThreadPool);
    }

    @Bean
    public FileHandlerFactory fileHandlerFactory(FileHandler smallFileHandler, FileHandler largeFileHandler) {
        return new FileHandlerFactory(largeFileHandler, smallFileHandler, fastDfsProperties);
    }
}
