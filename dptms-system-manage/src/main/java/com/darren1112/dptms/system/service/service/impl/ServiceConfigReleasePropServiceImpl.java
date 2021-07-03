package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.system.service.dao.ServiceConfigReleasePropDao;
import com.darren1112.dptms.system.service.service.ServiceConfigReleasePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置发布属性表ServiceImpl
 *
 * @author luyuhao
 * @since 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigReleaseProp", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigReleasePropServiceImpl implements ServiceConfigReleasePropService {

    @Autowired
    private ServiceConfigReleasePropDao serviceConfigReleasePropDao;

    /**
     * 查询配置发布属性list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigReleasePropDto}
     * @author luyuhao
     * @since 2021/3/17 8:58
     */
    @Override
    @Cacheable
    public List<ServiceConfigReleasePropDto> list(ServiceConfigReleasePropDto dto) {
        return serviceConfigReleasePropDao.list(dto);
    }

    /**
     * 批量新增发布属性
     *
     * @param releasePropList 发布熟悉集合
     * @author luyuhao
     * @since 2021/7/3
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void batchInsert(List<ServiceConfigReleasePropDto> releasePropList) {
        serviceConfigReleasePropDao.batchInsert(releasePropList);
    }
}
