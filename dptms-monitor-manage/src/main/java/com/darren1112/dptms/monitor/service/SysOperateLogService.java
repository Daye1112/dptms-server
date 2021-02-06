package com.darren1112.dptms.monitor.service;

import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;

/**
 * 操作日志Service
 *
 * @author luyuhao
 * @date 2021/02/06 20:43
 */
public interface SysOperateLogService {

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 21:08
     */
    void insert(SysOperateLogDto dto);
}
