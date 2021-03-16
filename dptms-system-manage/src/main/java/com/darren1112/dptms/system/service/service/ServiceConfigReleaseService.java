package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;

/**
 * 配置发布表Service
 *
 * @author luyuhao
 * @since 2021/03/12 01:45
 */
public interface ServiceConfigReleaseService {

    /**
     * 分页查询发布列表
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ServiceConfigReleaseDto}
     * @author luyuhao
     * @since 2021/3/16 8:34
     */
    PageBean<ServiceConfigReleaseDto> listPage(PageParam pageParam, ServiceConfigReleaseDto dto);

    /**
     * 插入发布信息
     *
     * @param dto 发布信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/3/16 8:46
     */
    Long insert(ServiceConfigReleaseDto dto);

    /**
     * 删除发布信息
     *
     * @param id      发布id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/3/16 8:58
     */
    void deleteById(Long id, Long updater);
}
