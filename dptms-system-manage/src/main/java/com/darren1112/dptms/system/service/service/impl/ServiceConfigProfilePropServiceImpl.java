package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import com.darren1112.dptms.system.service.dao.ServiceConfigProfilePropDao;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置环境属性表ServiceImpl
 *
 * @author luyuhao
 * @date 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigProfileProp", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigProfilePropServiceImpl implements ServiceConfigProfilePropService {

    @Autowired
    private ServiceConfigProfilePropDao serviceConfigProfilePropDao;

    /**
     * 查询服务应用list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @date 2021/03/13 01:50
     */
    @Override
    @Cacheable
    public List<ServiceConfigProfileDto> list(ServiceConfigProfileDto dto) {
        return serviceConfigProfilePropDao.list(dto);
    }
}
