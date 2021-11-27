package com.darren1112.dptms.monitor.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;

import java.util.List;

/**
 * 登录日志Service
 *
 * @author luyuhao
 * @since 2021/02/06 20:43
 */
public interface SysLoginLogService {

    /**
     * 插入登录日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @since 2021/02/06 20:53
     */
    void insert(SysLoginLogDto dto);

    /**
     * 分页查询登录日志
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link SysLoginLogDto}
     * @author luyuhao
     * @since 2021/02/10 00:13
     */
    PageBean<SysLoginLogDto> listPage(PageParam pageParam, SysLoginLogDto dto);

    /**
     * 查询最后7条登录记录
     *
     * @param userId 用户id
     * @return {@link SysLoginLogDto}
     * @author luyuhao
     * @since 2021/11/27
     */
    List<SysLoginLogDto> listLastSevenByUserId(Long userId);
}
