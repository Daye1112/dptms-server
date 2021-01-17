package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.dao.SysPermissionDao;
import com.darren1112.dptms.auth.service.SysPermissionService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限ServiceImpl
 *
 * @author luyuhao
 * @date 2020/12/09 23:44
 */
@Service
@CacheConfig(cacheNames = "sysPermission", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysPermissionServiceImpl extends BaseService implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    /**
     * 根据用户id查询权限list
     *
     * @param userId 用户id
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/17 21:30
     */
    @Override
    public List<SysPermissionDto> listByUserId(Long userId) {
        return sysPermissionDao.listByUserId(userId);
    }
}
