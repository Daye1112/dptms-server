package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;

import java.util.List;

/**
 * 配置环境表Service
 *
 * @author luyuhao
 * @date 2021/03/12 01:45
 */
public interface ServiceConfigProfileService {
    /**
     * 查询服务应用list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @date 2021/03/13 01:50
     */
    List<ServiceConfigProfileDto> list(ServiceConfigProfileDto dto);

    /**
     * 插入配置环境
     *
     * @param dto 配置环境信息
     * @return {@link Long}
     * @author luyuhao
     * @date 2021/03/14 02:03
     */
    Long insert(ServiceConfigProfileDto dto);

    /**
     * 更新配置环境
     *
     * @param dto 配置环境信息
     * @author luyuhao
     * @date 2021/03/14 02:14
     */
    void update(ServiceConfigProfileDto dto);

    /**
     * 根据id删除配置环境
     *
     * @param id      配置环境id
     * @param updater 更新者id
     * @author luyuhao
     * @date 2021/03/14 02:18
     */
    void deleteById(Long id, Long updater);
}
