package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.dao.SysUserDao;
import com.darren1112.dptms.auth.service.SysUserService;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @date 2020/07/23 02:43
 */
@Service
@CacheConfig(cacheNames = "sysUser")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @date 20/07/23 02:58
     */
    @Override
    @Cacheable(keyGenerator = "keyGenerator", unless = "#result == null")
    public SysUserDto getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    /**
     * 更新登录时间
     *
     * @param id 用户id
     * @author luyuhao
     * @date 20/12/09 01:02
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void updateLoginTime(Long id) {
        sysUserDao.updateLastLoginTime(id);
    }
}
