package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.dao.SysMenuDao;
import com.darren1112.dptms.auth.service.SysMenuService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单ServiceImpl
 *
 * @author luyuhao
 * @since 2020/12/12 17:25
 */
@Service
@CacheConfig(cacheNames = "sysMenu", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysMenuServiceImpl extends BaseService implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 获取用户的菜单
     *
     * @param userId 用户id
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    @Override
    public List<SysMenuDto> listMenuByUserId(Long userId) {
        return sysMenuDao.listMenuByUserId(userId);
    }
}
