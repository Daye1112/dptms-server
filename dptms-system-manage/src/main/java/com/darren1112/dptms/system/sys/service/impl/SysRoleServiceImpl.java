package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.system.sys.dao.SysRoleDao;
import com.darren1112.dptms.system.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色ServiceImpl
 *
 * @author luyuhao
 * @date 2020/12/13 23:09
 */
@Service
@CacheConfig(cacheNames = "sysRole", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

}
