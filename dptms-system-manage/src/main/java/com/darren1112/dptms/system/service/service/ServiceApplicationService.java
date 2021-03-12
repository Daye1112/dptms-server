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
}
