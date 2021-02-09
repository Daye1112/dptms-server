package com.darren1112.dptms.monitor.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;

/**
 * 登录日志Service
 *
 * @author luyuhao
 * @date 2021/02/06 20:43
 */
public interface SysLoginLogService {

    /**
     * 插入登录日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 20:53
     */
    void insert(SysLoginLogDto dto);

    /**
     * 分页查询登录日志
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link SysLoginLogDto}
     * @author luyuhao
     * @date 2021/02/10 00:13
     */
    PageBean<SysLoginLogDto> listPage(PageParam pageParam, SysLoginLogDto dto);
}
