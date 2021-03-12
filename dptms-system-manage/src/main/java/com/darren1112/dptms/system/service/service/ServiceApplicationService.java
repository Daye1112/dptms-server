package com.darren1112.dptms.system.service.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;

/**
 * 应用表Service
 *
 * @author luyuhao
 * @date 2021/03/12 01:31
 */
public interface ServiceApplicationService {

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    PageBean<ServiceApplicationDto> listPage(PageParam pageParam, ServiceApplicationDto dto);

    /**
     * 插入服务应用
     *
     * @param dto 服务信息
     * @return {@link Long}
     * @author luyuhao
     * @date 2021/03/12 23:28
     */
    Long insert(ServiceApplicationDto dto);

    /**
     * 更新服务应用
     *
     * @param dto 服务信息
     * @author luyuhao
     * @date 2021/03/12 23:42
     */
    void update(ServiceApplicationDto dto);

    /**
     * 根据id删除服务
     *
     * @param id      服务id
     * @param updater 更新者
     * @author luyuhao
     * @date 2021/03/13 00:49
     */
    void deleteById(Long id, Long updater);
}
