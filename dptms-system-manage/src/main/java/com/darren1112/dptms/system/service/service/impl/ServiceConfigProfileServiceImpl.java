package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.repository.ServiceConfigProfileRepository;
import com.darren1112.dptms.system.service.service.ServiceConfigProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置环境表ServiceImpl
 *
 * @author luyuhao
 * @since 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigProfile", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigProfileServiceImpl implements ServiceConfigProfileService {

    @Autowired
    private ServiceConfigProfileRepository serviceConfigProfileRepository;

    /**
     * 查询服务应用list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @since 2021/03/13 01:50
     */
    @Override
    @Cacheable
    public List<ServiceConfigProfileDto> list(ServiceConfigProfileDto dto) {
        return serviceConfigProfileRepository.getBaseMapper().list(dto);
    }

    /**
     * 插入配置环境
     *
     * @param dto 配置环境信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(ServiceConfigProfileDto dto) {
        validRepeat(dto, false);
        serviceConfigProfileRepository.getBaseMapper().insert(dto);
        return dto.getId();
    }

    /**
     * 验证配置环境是否重复
     *
     * @param dto      验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2021/03/14 02:04
     */
    private void validRepeat(ServiceConfigProfileDto dto, boolean isUpdate) {
        ServiceConfigProfileDto param = new ServiceConfigProfileDto();
        param.setId(dto.getId());
        param.setApplicationId(dto.getApplicationId());
        param.setProfileCode(dto.getProfileCode());
        param.setIsUpdate(isUpdate);
        Long count = serviceConfigProfileRepository.getBaseMapper().countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.PROFILE_NOT_REPEAT);
        }
    }

    /**
     * 更新配置环境
     *
     * @param dto 配置环境信息
     * @author luyuhao
     * @since 2021/03/14 02:14
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void update(ServiceConfigProfileDto dto) {
        validRepeat(dto, true);
        serviceConfigProfileRepository.getBaseMapper().update(dto);
    }

    /**
     * 根据id删除配置环境
     *
     * @param id      配置环境id
     * @param updater 更新者id
     * @author luyuhao
     * @since 2021/03/14 02:18
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        serviceConfigProfileRepository.getBaseMapper().deleteById(id, updater);
    }
}
