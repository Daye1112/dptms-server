package com.darren1112.dptms.common.log.starter.collect;

import com.darren1112.dptms.common.spi.monitor.dto.MonitorLoginLogDto;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorOperateLogDto;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志收集基础类
 *
 * @author luyuhao
 * @since 2021/02/07 00:29
 */
public interface LogCollectService {

    /**
     * 操作日志收集
     *
     * @param dto 日志细信息
     * @author luyuhao
     * @since 2021/02/07 00:31
     */
    @Async("logCollectThreadPool")
    void operateLogCollect(MonitorOperateLogDto dto);

    /**
     * 插入登录日志信息
     *
     * @param dto 日志参数
     * @author luyuhao
     * @since 2021/02/06 20:50
     */
    @Async("logCollectThreadPool")
    void loginLogCollect(MonitorLoginLogDto dto);
}
