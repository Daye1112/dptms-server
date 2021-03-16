package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.dao.ServiceConfigReleaseDao;
import com.darren1112.dptms.system.service.service.ServiceConfigReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置发布表ServiceImpl
 *
 * @author luyuhao
 * @date 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigRelease", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigReleaseServiceImpl extends BaseService implements ServiceConfigReleaseService {

    @Autowired
    private ServiceConfigReleaseDao serviceConfigReleaseDao;

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
        List<ServiceConfigReleaseDto> list = serviceConfigReleaseDao.listPage(pageParam, dto);
        Long count = serviceConfigReleaseDao.listPageCount(dto);
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
        validRepeat(dto, false);
        serviceConfigReleaseDao.insert(dto);
        return dto.getId();
    }

    /**
     * 验证发布版本是否重复
     *
     * @param dto      验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2021/3/16 8:46
     */
    private void validRepeat(ServiceConfigReleaseDto dto, boolean isUpdate) {
        ServiceConfigReleaseDto param = new ServiceConfigReleaseDto();
        param.setId(dto.getId());
        param.setApplicationId(dto.getApplicationId());
        param.setProfileId(dto.getProfileId());
        param.setReleaseVersion(dto.getReleaseVersion());
        param.setIsUpdate(isUpdate);
        Long count = serviceConfigReleaseDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.RELEASE_NOT_REPEAT);
        }
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
        serviceConfigReleaseDao.deleteById(id, updater);
    }
}
