package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.repository.AuthOrganizationRepository;
import com.darren1112.dptms.auth.service.AuthOrganizationService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthOrganizationEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
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
 * @since 2020/08/16 01:43
 */
@Service
@CacheConfig(cacheNames = "sysOrganization", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class AuthOrganizationServiceImpl extends BaseService implements AuthOrganizationService {

    @Autowired
    private AuthOrganizationRepository authOrganizationRepository;

    /**
     * 分页查询组织信息
     *
     * @param pageParam 分页参数
     * @param param     筛选参数
     * @return {@link AuthOrganizationDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @Cacheable
    public PageBean<AuthOrganizationDto> listPage(PageParam pageParam, AuthOrganizationDto param) {
        List<AuthOrganizationDto> list = authOrganizationRepository.getBaseMapper().listPage(pageParam, param);
        Long count = authOrganizationRepository.getBaseMapper().listPageCount(param);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 插入组织信息
     *
     * @param dto 组织参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(AuthOrganizationDto dto) {
        validRepeat(dto, false);
        authOrganizationRepository.getBaseMapper().insert(dto);
        return dto.getId();
    }

    /**
     * 校验是否重复
     *
     * @param entity   参数
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 20/12/12 22:04
     */
    private void validRepeat(AuthOrganizationEntity entity, boolean isUpdate) {
        AuthOrganizationDto param = new AuthOrganizationDto();
        param.setIsUpdate(isUpdate);
        param.setOrgCode(entity.getOrgCode());
        param.setOrgName(entity.getOrgName());
        param.setId(entity.getId());
        Long count = authOrganizationRepository.getBaseMapper().countByRepeat(param);
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
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(AuthOrganizationEntity entity) {
        validRepeat(entity, true);
        return authOrganizationRepository.getBaseMapper().update(entity);
    }

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        authOrganizationRepository.getBaseMapper().deleteById(id, updater);
    }
}
