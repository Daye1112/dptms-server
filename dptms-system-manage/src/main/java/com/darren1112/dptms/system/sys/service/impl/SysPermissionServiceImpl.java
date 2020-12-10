package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import com.darren1112.dptms.system.sys.dao.SysPermissionDao;
import com.darren1112.dptms.system.sys.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "sysPermission")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysPermissionServiceImpl extends BaseService implements SysPermissionService {

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

    /**
     * 分页查询权限
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @Cacheable(keyGenerator = "keyGenerator")
    public PageBean<SysPermissionDto> listPage(PageParam pageParam, SysPermissionDto dto) {
        List<SysPermissionDto> list = sysPermissionDao.listPage(pageParam, dto);
        Long count = sysPermissionDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }
}
