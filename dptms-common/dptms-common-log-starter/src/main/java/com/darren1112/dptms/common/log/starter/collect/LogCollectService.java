package com.darren1112.dptms.common.log.starter.collect;

import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志收集基础类
 *
 * @author luyuhao
 * @date 2021/02/07 00:29
 */
public interface LogCollectService {

    /**
     * 操作日志收集
     *
     * @param dto 日志细信息
     * @author luyuhao
     * @date 2021/02/07 00:31
     */
    @Async("logCollectThreadPool")
    void operateLogCollect(SysOperateLogDto dto);
}
