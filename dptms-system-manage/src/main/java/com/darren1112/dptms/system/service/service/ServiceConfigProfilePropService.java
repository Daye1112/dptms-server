package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;

import java.util.List;

/**
 * 配置环境属性表Service
 *
 * @author luyuhao
 * @date 2021/03/12 01:45
 */
public interface ServiceConfigProfilePropService {

    /**
     * 查询服务应用list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @date 2021/03/13 01:50
     */
    List<ServiceConfigProfileDto> list(ServiceConfigProfileDto dto);
}
