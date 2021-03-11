package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.system.service.dao.ServiceConfigReleaseDao;
import com.darren1112.dptms.system.service.service.ServiceConfigReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置发布表ServiceImpl
 *
 * @author luyuhao
 * @date 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigRelease", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigReleaseServiceImpl implements ServiceConfigReleaseService {

    @Autowired
    private ServiceConfigReleaseDao serviceConfigReleaseDao;
}
