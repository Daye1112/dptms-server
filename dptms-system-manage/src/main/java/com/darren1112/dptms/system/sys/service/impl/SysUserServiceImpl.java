package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.dao.SysUserDao;
import com.darren1112.dptms.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户ServiceImpl
 *
 * @author luyuhao
 * @date 2020/07/23 02:43
 */
@Service
@CacheConfig(cacheNames = "sysUser", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/11/30 23:12
     */
    @Override
    @Cacheable
    public SysUserDto getById(Long id) {
        return sysUserDao.getById(id);
    }

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(SysUserEntity entity) {
        validRepeat(entity, false);
        sysUserDao.insert(entity);
        return entity.getId();
    }

    /**
     * 验证code和url是否重复
     *
     * @param entity   验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @date 2020/12/12 11:06
     */
    private void validRepeat(SysUserEntity entity, boolean isUpdate) {
        SysUserDto param = new SysUserDto();
        param.setId(entity.getId());
        param.setUsername(entity.getUsername());
        param.setIsUpdate(isUpdate);
        Long count = sysUserDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.USER_NOT_REPEAT);
        }
    }

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @Cacheable
    public PageBean<SysUserDto> listPage(PageParam pageParam, SysUserDto dto) {
        List<SysUserDto> list = sysUserDao.listPage(pageParam, dto);
        Long count = sysUserDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(SysUserEntity entity) {
        validRepeat(entity, true);
        return sysUserDao.update(entity);
    }

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        sysUserDao.deleteById(id, updater);
    }
}
