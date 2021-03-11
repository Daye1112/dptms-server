package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.system.service.dao.ServiceConfigProfilePropDao;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
