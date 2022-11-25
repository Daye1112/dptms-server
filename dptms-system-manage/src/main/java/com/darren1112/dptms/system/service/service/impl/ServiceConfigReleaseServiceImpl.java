package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.DateUtil;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.repository.ServiceConfigReleaseRepository;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
import com.darren1112.dptms.system.service.service.ServiceConfigReleasePropService;
import com.darren1112.dptms.system.service.service.ServiceConfigReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 配置发布表ServiceImpl
 *
 * @author luyuhao
 * @since 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigRelease", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigReleaseServiceImpl extends BaseService implements ServiceConfigReleaseService {

    @Autowired
    private ServiceConfigReleaseRepository serviceConfigReleaseRepository;

    @Lazy
    @Autowired
    private ServiceConfigProfilePropService serviceConfigProfilePropService;

    @Lazy
    @Autowired
    private ServiceConfigReleasePropService serviceConfigReleasePropService;

    /**
     * 分页查询发布列表
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ServiceConfigReleaseDto}
     * @author luyuhao
     * @since 2021/3/16 8:34
     */
    @Override
    @Cacheable
    public PageBean<ServiceConfigReleaseDto> listPage(PageParam pageParam, ServiceConfigReleaseDto dto) {
        List<ServiceConfigReleaseDto> list = serviceConfigReleaseRepository.getBaseMapper().listPage(pageParam, dto);
        Long count = serviceConfigReleaseRepository.getBaseMapper().listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 插入发布信息
     *
     * @param dto 发布信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/3/16 8:46
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(ServiceConfigReleaseDto dto) {
        // 查询环境属性集合
        List<ServiceConfigProfilePropDto> profilePropList = serviceConfigProfilePropService.listByProfileId(dto.getProfileId());
        if (CollectionUtil.isEmpty(profilePropList)) {
            throw new BadRequestException(SystemManageErrorCodeEnum.PROP_NOT_EXIST);
        }
        // 新增发布信息
        dto.setReleaseVersion(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        serviceConfigReleaseRepository.getBaseMapper().insert(dto);

        // 拷贝属性到发布属性表
        List<ServiceConfigReleasePropDto> releasePropList = new ArrayList<>();
        profilePropList.forEach(e -> {
            ServiceConfigReleasePropDto releaseProp = new ServiceConfigReleasePropDto();
            releaseProp.setReleaseId(dto.getId());
            releaseProp.setPropKey(e.getPropKey());
            releaseProp.setPropValue(e.getPropValue());
            releaseProp.setPropDesc(e.getPropDesc());
            releaseProp.setCreater(dto.getCreater());
            releaseProp.setUpdater(dto.getUpdater());
            releasePropList.add(releaseProp);
        });
        serviceConfigReleasePropService.batchInsert(releasePropList);
        return dto.getId();
    }

    /**
     * 删除发布信息
     *
     * @param id      发布id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/3/16 8:58
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        serviceConfigReleaseRepository.getBaseMapper().deleteById(id, updater);
    }
}
