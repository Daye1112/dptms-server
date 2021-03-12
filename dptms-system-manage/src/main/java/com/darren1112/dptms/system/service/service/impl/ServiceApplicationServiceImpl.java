package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.dao.ServiceApplicationDao;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用表ServiceImpl
 *
 * @author luyuhao
 * @date 2021/03/12 01:31
 */
@Service
@CacheConfig(cacheNames = "serviceApplication", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceApplicationServiceImpl extends BaseService implements ServiceApplicationService {

    @Autowired
    private ServiceApplicationDao serviceApplicationDao;

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    @Override
    @Cacheable
    public PageBean<ServiceApplicationDto> listPage(PageParam pageParam, ServiceApplicationDto dto) {
        List<ServiceApplicationDto> list = serviceApplicationDao.listPage(pageParam, dto);
        Long count = serviceApplicationDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 插入服务应用
     *
     * @param dto 服务信息
     * @return {@link Long}
     * @author luyuhao
     * @date 2021/03/12 23:28
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(ServiceApplicationDto dto) {
        validRepeat(dto, false);
        serviceApplicationDao.insert(dto);
        return dto.getId();
    }

    /**
     * 验证服务是否重复
     *
     * @param dto      验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @date 2021/03/12 23:28
     */
    private void validRepeat(ServiceApplicationDto dto, boolean isUpdate) {
        ServiceApplicationDto param = new ServiceApplicationDto();
        param.setId(dto.getId());
        param.setAppCode(dto.getAppCode());
        param.setAppName(dto.getAppName());
        param.setIsUpdate(isUpdate);
        Long count = serviceApplicationDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.APP_NOT_REPEAT);
        }
    }

    /**
     * 更新服务应用
     *
     * @param dto 服务信息
     * @author luyuhao
     * @date 2021/03/12 23:42
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void update(ServiceApplicationDto dto) {
        validRepeat(dto, true);
        serviceApplicationDao.update(dto);
    }

    /**
     * 根据id删除服务
     *
     * @param id      服务id
     * @param updater 更新者
     * @author luyuhao
     * @date 2021/03/13 00:49
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        serviceApplicationDao.deleteById(id, updater);
    }
}
