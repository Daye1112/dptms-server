package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.dao.SysPermissionDao;
import com.darren1112.dptms.auth.service.SysPermissionService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
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
        validRepeat(entity, false);
        sysPermissionDao.insert(entity);
        return entity.getId();
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
    @Cacheable
    public PageBean<SysPermissionDto> listPage(PageParam pageParam, SysPermissionDto dto) {
        List<SysPermissionDto> list = sysPermissionDao.listPage(pageParam, dto);
        Long count = sysPermissionDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 更新权限信息
     *
     * @param entity 权限参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(SysPermissionEntity entity) {
        validRepeat(entity, true);
        return sysPermissionDao.update(entity);
    }

    /**
     * 验证code和url是否重复
     *
     * @param entity   验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @date 2020/12/12 11:06
     */
    private void validRepeat(SysPermissionEntity entity, boolean isUpdate) {
        SysPermissionDto param = new SysPermissionDto();
        param.setId(entity.getId());
        param.setPerCode(entity.getPerCode());
        param.setPerUrl(entity.getPerUrl());
        param.setIsUpdate(isUpdate);
        Long count = sysPermissionDao.countByRepeat(param);
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
     * @date 20/12/12 20:44
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        sysPermissionDao.deleteById(id, updater);
    }

    /**
     * 查询权限组list
     *
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2020/12/28 01:10
     */
    @Override
    @Cacheable
    public List<SysPermissionDto> listGroup() {
        return sysPermissionDao.listGroup();
    }
}
