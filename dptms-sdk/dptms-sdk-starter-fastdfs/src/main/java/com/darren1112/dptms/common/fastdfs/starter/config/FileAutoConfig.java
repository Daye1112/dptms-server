package com.darren1112.dptms.common.fastdfs.starter.config;

import com.darren1112.dptms.common.core.thread.decorator.ThreadPoolDecorator;
import com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.FileClient;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.impl.CommonFileClient;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.FileHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.FileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.LargeFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.SmallFileHandler;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 文件相关自动装配类
 *
 * @author luyuhao
 * @since 2021/12/8
 */
@EnableConfigurationProperties(FastDfsProperties.class)
public class FileAutoConfig {

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
    public FileClient fileClient(FileHandlerFactory fileHandlerFactory) {
        return new CommonFileClient(fileHandlerFactory);
    }

    @Bean("smallFileHandler")
    public FileHandler smallFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory) {
        return new SmallFileHandler(fastDfsHandlerFactory);
    }

    @Bean("largeFileHandler")
    public FileHandler largeFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory, ThreadPoolTaskExecutor fileHandleThreadPool) {
        return new LargeFileHandler(fastDfsHandlerFactory, fastDfsProperties, fileHandleThreadPool);
    }

    @Bean
    public FileHandlerFactory fileHandlerFactory(FileHandler smallFileHandler, FileHandler largeFileHandler) {
        return new FileHandlerFactory(largeFileHandler, smallFileHandler, fastDfsProperties);
    }
}
