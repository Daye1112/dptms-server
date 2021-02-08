package com.darren1112.dptms.common.log.starter.config;

import com.darren1112.dptms.common.core.thread.decorator.ThreadPoolDecorator;
import com.darren1112.dptms.common.log.starter.aspect.LogAspect;
import com.darren1112.dptms.common.log.starter.collect.LogCollectService;
import com.darren1112.dptms.common.log.starter.collect.impl.FeignLogCollectServiceImpl;
import com.darren1112.dptms.common.log.starter.enums.LogCollectType;
import com.darren1112.dptms.common.log.starter.properties.LogProperties;
import com.darren1112.dptms.common.log.starter.remoting.MonitorManageRemoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 日志自动配置类
 *
 * @author luyuhao
 * @date 2021/02/07 01:59
 */
@EnableAsync
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfig {

    @Autowired
    private LogProperties logProperties;

    /**
     * 日志采集线程池配置
     *
     * @return {@link ThreadPoolTaskExecutor}
     * @author luyuhao
     * @date 2021/02/07 02:09
     */
    @Bean
    public ThreadPoolTaskExecutor logCollectThreadPool() {
        ThreadPoolTaskExecutor logCollectThreadPool = new ThreadPoolTaskExecutor();
        logCollectThreadPool.setMaxPoolSize(logProperties.getLogMaxPoolSize());
        logCollectThreadPool.setCorePoolSize(logCollectThreadPool.getCorePoolSize());
        logCollectThreadPool.setThreadNamePrefix("logCollectExecutor-");
        // 传递上下文
        logCollectThreadPool.setTaskDecorator(new ThreadPoolDecorator());
        // 当线程池已满,且等待队列也满了的时候,转为主线程执行
        logCollectThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        logCollectThreadPool.initialize();
        return logCollectThreadPool;
    }

    @Bean
    public LogCollectService logCollectService(MonitorManageRemoting monitorManageRemoting) {
        LogCollectType logCollectType = logProperties.getLogCollectType();
        switch (logCollectType) {
            case FEIGN:
                return new FeignLogCollectServiceImpl(monitorManageRemoting);
            default:
                return new FeignLogCollectServiceImpl(monitorManageRemoting);
        }
    }

    @Bean
    public LogAspect logAspect(LogCollectService logCollectService) {
        return new LogAspect(logProperties, logCollectService);
    }
}
