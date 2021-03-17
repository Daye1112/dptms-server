package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;

import java.util.List;

/**
 * 配置发布属性表Service
 *
 * @author luyuhao
 * @since 2021/03/12 01:45
 */
public interface ServiceConfigReleasePropService {

    /**
     * 查询配置发布属性list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigReleasePropDto}
     * @author luyuhao
     * @since 2021/3/17 8:58
     */
    List<ServiceConfigReleasePropDto> list(ServiceConfigReleasePropDto dto);
}
