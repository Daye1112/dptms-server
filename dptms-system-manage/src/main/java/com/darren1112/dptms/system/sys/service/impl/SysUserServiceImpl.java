package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.mvc.BaseService;
import com.darren1112.dptms.system.sys.dao.SysUserDao;
import com.darren1112.dptms.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户ServiceImpl
 *
 * @author luyuhao
 * @date 2020/07/23 02:43
 */
@Service
@CacheConfig(cacheNames = "sysUser")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

}
