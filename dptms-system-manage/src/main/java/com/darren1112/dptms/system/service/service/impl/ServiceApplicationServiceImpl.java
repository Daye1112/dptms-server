package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.system.service.dao.ServiceApplicationDao;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
