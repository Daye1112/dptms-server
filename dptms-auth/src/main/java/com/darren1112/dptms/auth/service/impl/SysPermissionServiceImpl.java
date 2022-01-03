package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.dao.AuthPermissionDao;
import com.darren1112.dptms.auth.service.SysPermissionService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthPermissionEntity;
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
 * @since 2020/12/09 23:44
 */
@Service
@CacheConfig(cacheNames = "sysPermission", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysPermissionServiceImpl extends BaseService implements SysPermissionService {

    @Autowired
    private AuthPermissionDao authPermissionDao;

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link Long 权限id}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(AuthPermissionEntity entity) {
        validRepeat(entity, false);
        authPermissionDao.insert(entity);
        return entity.getId();
    }

    /**
     * 分页查询权限
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @Cacheable
    public PageBean<AuthPermissionDto> listPage(PageParam pageParam, AuthPermissionDto dto) {
        List<AuthPermissionDto> list = authPermissionDao.listPage(pageParam, dto);
        Long count = authPermissionDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 更新权限信息
     *
     * @param entity 权限参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(AuthPermissionEntity entity) {
        validRepeat(entity, true);
        return authPermissionDao.update(entity);
    }

    /**
     * 验证code和url是否重复
     *
     * @param entity   验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2020/12/12 11:06
     */
    private void validRepeat(AuthPermissionEntity entity, boolean isUpdate) {
        AuthPermissionDto param = new AuthPermissionDto();
        param.setId(entity.getId());
        param.setPerCode(entity.getPerCode());
        param.setPerUrl(entity.getPerUrl());
        param.setIsUpdate(isUpdate);
        Long count = authPermissionDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.PER_NOT_REPEAT);
        }
    }

    /**
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/12 20:44
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        authPermissionDao.deleteById(id, updater);
    }

    /**
     * 查询权限组list
     *
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2020/12/28 01:10
     */
    @Override
    @Cacheable
    public List<AuthPermissionDto> listGroup() {
        return authPermissionDao.listGroup();
    }
}
