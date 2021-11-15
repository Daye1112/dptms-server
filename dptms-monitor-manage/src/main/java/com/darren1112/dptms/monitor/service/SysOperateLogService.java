package com.darren1112.dptms.monitor.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;

/**
 * 操作日志Service
 *
 * @author luyuhao
 * @since 2021/02/06 20:43
 */
public interface SysOperateLogService {

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @since 2021/02/06 21:08
     */
    void insert(SysOperateLogDto dto);

    /**
     * 分页查询操作日志
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysOperateLogDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    PageBean<SysOperateLogDto> listPage(PageParam pageParam, SysOperateLogDto dto);
}
