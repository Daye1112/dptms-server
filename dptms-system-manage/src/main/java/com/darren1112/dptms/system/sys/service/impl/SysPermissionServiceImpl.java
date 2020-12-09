package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import com.darren1112.dptms.system.sys.dao.SysPermissionDao;
import com.darren1112.dptms.system.sys.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限ServiceImpl
 *
 * @author luyuhao
 * @date 2020/12/09 23:44
 */
@Service
@CacheConfig(cacheNames = "sysPermission")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link Long 权限id}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(SysPermissionEntity entity) {
        return sysPermissionDao.insert(entity);
    }
}
