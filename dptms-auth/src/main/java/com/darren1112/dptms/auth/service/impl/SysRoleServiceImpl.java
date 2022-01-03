package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.dao.AuthRoleDao;
import com.darren1112.dptms.auth.service.SysRoleService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色ServiceImpl
 *
 * @author luyuhao
 * @since 2020/12/13 23:09
 */
@Service
@CacheConfig(cacheNames = "sysRole", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

    @Autowired
    private AuthRoleDao authRoleDao;

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long 角色id)
     * @author baojiazhong
     * @since 2020/12/19 1:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(AuthRoleEntity entity) {
        validRepeat(entity, false);
        authRoleDao.insert(entity);
        return entity.getId();
    }

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/19 20:07
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(AuthRoleEntity entity) {
        validRepeat(entity, true);
        return authRoleDao.update(entity);
    }

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link AuthRoleDto )
     * @author baojiazhong
     * @since 2020/12/19 21:10
     */
    @Override
    @Cacheable
    public PageBean<AuthRoleDto> listPage(PageParam pageParam, AuthRoleDto dto) {
        List<AuthRoleDto> list = authRoleDao.listPage(pageParam, dto);
        Long count = authRoleDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/19 21:11
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        authRoleDao.deleteById(id, updater);
    }

    /**
     * 验证code是否重复
     *
     * @param entity   验证参数
     * @param isUpdate 是否更新
     * @author baojiazhong
     * @since 2020/12/19 1:12
     */
    private void validRepeat(AuthRoleEntity entity, boolean isUpdate) {
        AuthRoleDto param = new AuthRoleDto();
        param.setId(entity.getId());
        param.setRoleCode(entity.getRoleCode());
        param.setIsUpdate(isUpdate);
        Long count = authRoleDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.ROLE_NOT_REPEAT);
        }
    }
}
