package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;

import java.util.List;

/**
 * 配置环境属性表Service
 *
 * @author luyuhao
 * @date 2021/03/12 01:45
 */
public interface ServiceConfigProfilePropService {

    /**
     * 查询配置环境属性list
     *
     * @param dto 配置环境属性
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @date 2021/03/14 22:57
     */
    List<ServiceConfigProfilePropDto> list(ServiceConfigProfilePropDto dto);

    /**
     * 插入配置环境属性
     *
     * @param dto 配置环境属性信息
     * @return {@link Long}
     * @author luyuhao
     * @date 2021/03/14 23:01
     */
    Long insert(ServiceConfigProfilePropDto dto);

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author luyuhao
     * @date 2021/03/14 23:08
     */
    void update(ServiceConfigProfilePropDto dto);

    /**
     * 根据id删除配置环境属性
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @date 2021/03/14 23:11
     */
    void deleteById(Long id, Long updater);
}
