package com.darren1112.dptms.system.project.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.project.dto.ProjectInfoDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.project.repository.ProjectInfoRepository;
import com.darren1112.dptms.system.project.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目信息ServiceImpl
 *
 * @author darren
 * @since 2023/08/13
 */
@Service
@CacheConfig(cacheNames = "projectInfo", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ProjectInfoServiceImpl extends BaseService implements ProjectInfoService {

    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    /**
     * 分页查询项目信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ProjectInfoDto}
     * @author darren
     * @since 2023/08/13
     */
    @Override
    @Cacheable
    public PageBean<ProjectInfoDto> listPage(PageParam pageParam, ProjectInfoDto dto) {
        List<ProjectInfoDto> list = projectInfoRepository.listPage(pageParam, dto);
        Long count = projectInfoRepository.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 新增项目信息
     *
     * @param dto 项目信息
     * @return {@link Long}
     * @author darren
     * @since 2023/08/13
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(ProjectInfoDto dto) {
        // 生成appKey
        String appKey = RandomUtil.randomStringUpper(32);
        dto.setProjectAppKey(appKey);
        validRepeat(dto, false);
        projectInfoRepository.getBaseMapper().insert(dto);
        return dto.getId();
    }

    /**
     * 更新项目信息
     *
     * @param dto 项目信息
     * @author darren
     * @since 2023/08/13
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void update(ProjectInfoDto dto) {
        validRepeat(dto, true);
        projectInfoRepository.updateInfo(dto);
    }

    /**
     * 验证项目名称是否重复
     *
     * @param dto      验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2021/03/12 23:28
     */
    private void validRepeat(ProjectInfoDto dto, boolean isUpdate) {
        ProjectInfoDto param = new ProjectInfoDto();
        param.setId(dto.getId());
        param.setProjectName(dto.getProjectName());
        param.setProjectAppKey(dto.getProjectAppKey());
        param.setIsUpdate(isUpdate);
        Long count = projectInfoRepository.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.APP_NOT_REPEAT);
        }
    }

    /**
     * 根据id删除项目信息
     *
     * @param id      数据id
     * @param updater 更新者
     * @author darren
     * @since 2023/08/13
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        projectInfoRepository.deleteById(id, updater);
    }
}
