package com.darren1112.dptms.monitor.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorOperateLogDto;

/**
 * 操作日志Service
 *
 * @author darren
 * @since 2021/02/06 20:43
 */
public interface MonitorOperateLogService {

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author darren
     * @since 2021/02/06 21:08
     */
    void insert(MonitorOperateLogDto dto);

    /**
     * 分页查询操作日志
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link MonitorOperateLogDto}
     * @author darren
     * @since 20/12/10 01:08
     */
    PageBean<MonitorOperateLogDto> listPage(PageParam pageParam, MonitorOperateLogDto dto);
}
