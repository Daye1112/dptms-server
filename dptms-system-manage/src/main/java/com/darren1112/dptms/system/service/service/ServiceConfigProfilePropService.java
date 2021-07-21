package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;

import java.util.List;

/**
 * 配置环境属性表Service
 *
 * @author luyuhao
 * @since 2021/03/12 01:45
 */
public interface ServiceConfigProfilePropService {

    /**
     * 查询配置环境属性list
     *
     * @param dto 配置环境属性
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/03/14 22:57
     */
    List<ServiceConfigProfilePropDto> list(ServiceConfigProfilePropDto dto);

    /**
     * 插入配置环境属性
     *
     * @param dto 配置环境属性信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 23:01
     */
    Long insert(ServiceConfigProfilePropDto dto);

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author luyuhao
     * @since 2021/03/14 23:08
     */
    void update(ServiceConfigProfilePropDto dto);

    /**
     * 根据id删除配置环境属性
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/03/14 23:11
     */
    void deleteById(Long id, Long updater);

    /**
     * 根据环境id查询属性集合
     *
     * @param profileId 环境id
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/7/3 10:40
     */
    List<ServiceConfigProfilePropDto> listByProfileId(Long profileId);

    /**
     * 批量导入配置环境属性
     *
     * @param dto content   文本内容
     *            profileId 环境id
     * @author luyuhao
     * @since 2021/7/21
     */
    void batchInsert(ServiceConfigProfilePropDto dto);
}
