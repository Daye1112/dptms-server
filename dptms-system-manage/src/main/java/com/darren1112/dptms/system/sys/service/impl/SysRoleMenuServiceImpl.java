package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.system.sys.service.SysRoleMenuService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单ServiceImpl
 *
 * @author luyuhao
 * @date 2020/12/13 23:09
 */
@Service
@CacheConfig(cacheNames = "sysRoleMenu", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
}
