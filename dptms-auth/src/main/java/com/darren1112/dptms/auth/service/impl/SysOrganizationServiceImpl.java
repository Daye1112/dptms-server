package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.dao.SysOrganizationDao;
import com.darren1112.dptms.auth.service.SysOrganizationService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysOrganizationDto;
import com.darren1112.dptms.common.spi.sys.entity.SysOrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织ServiceImpl
 *
 * @author luyuhao
 * @date 2020/08/16 01:43
 */
@Service
@CacheConfig(cacheNames = "sysOrganization", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysOrganizationServiceImpl extends BaseService implements SysOrganizationService {

    @Autowired
    private SysOrganizationDao sysOrganizationDao;

    /**
     * 分页查询组织信息
     *
     * @param pageParam 分页参数
     * @param param     筛选参数
     * @return {@link SysOrganizationDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @Cacheable
    public PageBean<SysOrganizationDto> listPage(PageParam pageParam, SysOrganizationDto param) {
        List<SysOrganizationDto> list = sysOrganizationDao.listPage(pageParam, param);
        Long count = sysOrganizationDao.listPageCount(param);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 插入组织信息
     *
     * @param entity 组织参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(SysOrganizationEntity entity) {
        validRepeat(entity, false);
        sysOrganizationDao.insert(entity);
        return entity.getId();
    }

    /**
     * 校验是否重复
     *
     * @param entity   参数
     * @param isUpdate 是否更新
     * @author luyuhao
     * @date 20/12/12 22:04
     */
    private void validRepeat(SysOrganizationEntity entity, boolean isUpdate) {
        SysOrganizationDto param = new SysOrganizationDto();
        param.setIsUpdate(isUpdate);
        param.setOrgCode(entity.getOrgCode());
        param.setOrgName(entity.getOrgName());
        param.setId(entity.getId());
        Long count = sysOrganizationDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.ORG_NOT_REPEAT);
        }
    }

    /**
     * 更新组织信息
     *
     * @param entity 组织参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(SysOrganizationEntity entity) {
        validRepeat(entity, true);
        return sysOrganizationDao.update(entity);
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
        sysOrganizationDao.deleteById(id, updater);
    }
}
