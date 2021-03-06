package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.dao.SysRoleDao;
import com.darren1112.dptms.auth.service.SysRoleService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;
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
    private SysRoleDao sysRoleDao;

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
    public Long insert(SysRoleEntity entity) {
        validRepeat(entity, false);
        sysRoleDao.insert(entity);
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
    public Long update(SysRoleEntity entity) {
        validRepeat(entity, true);
        return sysRoleDao.update(entity);
    }

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link SysRoleDto)
     * @author baojiazhong
     * @since 2020/12/19 21:10
     */
    @Override
    @Cacheable
    public PageBean<SysRoleDto> listPage(PageParam pageParam, SysRoleDto dto) {
        List<SysRoleDto> list = sysRoleDao.listPage(pageParam, dto);
        Long count = sysRoleDao.listPageCount(dto);
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
        sysRoleDao.deleteById(id, updater);
    }

    /**
     * 验证code是否重复
     *
     * @param entity   验证参数
     * @param isUpdate 是否更新
     * @author baojiazhong
     * @since 2020/12/19 1:12
     */
    private void validRepeat(SysRoleEntity entity, boolean isUpdate) {
        SysRoleDto param = new SysRoleDto();
        param.setId(entity.getId());
        param.setRoleCode(entity.getRoleCode());
        param.setIsUpdate(isUpdate);
        Long count = sysRoleDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.ROLE_NOT_REPEAT);
        }
    }
}
