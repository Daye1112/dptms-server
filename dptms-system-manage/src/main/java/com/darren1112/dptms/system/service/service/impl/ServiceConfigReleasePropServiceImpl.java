package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheEvict;
import com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheable;
import com.darren1112.dptms.system.service.repository.ServiceConfigReleasePropRepository;
import com.darren1112.dptms.system.service.service.ServiceConfigReleasePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置发布属性表ServiceImpl
 *
 * @author darren
 * @since 2021/03/12 01:47
 */
@Service
@RedisCacheable("serviceConfigReleaseProp")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigReleasePropServiceImpl implements ServiceConfigReleasePropService {

    @Autowired
    private ServiceConfigReleasePropRepository serviceConfigReleasePropRepository;

    /**
     * 查询配置发布属性list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigReleasePropDto}
     * @author darren
     * @since 2021/3/17 8:58
     */
    @Override
    @RedisCacheable
    public List<ServiceConfigReleasePropDto> list(ServiceConfigReleasePropDto dto) {
        return serviceConfigReleasePropRepository.getBaseMapper().list(dto);
    }

    /**
     * 批量新增发布属性
     *
     * @param releasePropList 发布熟悉集合
     * @author darren
     * @since 2021/7/3
     */
    @Override
    @RedisCacheEvict
    @Transactional(rollbackFor = Throwable.class)
    public void batchInsert(List<ServiceConfigReleasePropDto> releasePropList) {
        serviceConfigReleasePropRepository.getBaseMapper().batchInsert(releasePropList);
    }
}
