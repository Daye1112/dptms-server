package com.darren1112.dptms.component.service;

import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;

import java.util.List;

/**
 * druid service
 *
 * @author luyuhao
 * @date 2021/02/15 23:30
 */
public interface DruidService {

    /**
     * 查询接口统计list
     *
     * @return {@link DruidStatDto}
     * @author luyuhao
     * @date 2021/02/15 23:32
     */
    List<DruidStatDto> apiStatList();
}
