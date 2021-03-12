package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import com.darren1112.dptms.system.service.dao.ServiceApplicationDao;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用表ServiceImpl
 *
 * @author luyuhao
 * @date 2021/03/12 01:31
 */
@Service
@CacheConfig(cacheNames = "serviceApplication", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceApplicationServiceImpl extends BaseService implements ServiceApplicationService {

    @Autowired
    private ServiceApplicationDao serviceApplicationDao;

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    @Override
    @Cacheable
    public PageBean<ServiceApplicationDto> listPage(PageParam pageParam, ServiceApplicationDto dto) {
        List<ServiceApplicationDto> list = serviceApplicationDao.listPage(pageParam, dto);
        Long count = serviceApplicationDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }
}
