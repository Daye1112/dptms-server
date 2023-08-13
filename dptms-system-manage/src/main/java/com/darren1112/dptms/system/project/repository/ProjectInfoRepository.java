package com.darren1112.dptms.system.project.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.project.dto.ProjectInfoDto;
import com.darren1112.dptms.system.project.dao.ProjectInfoDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 项目信息Repository
 *
 * @author darren
 * @since 2023/08/13
 */
@Repository
public class ProjectInfoRepository extends ServiceImpl<ProjectInfoDao, ProjectInfoDto> {

    /**
     * 分页查询项目信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ProjectInfoDto}
     * @author darren
     * @since 2023/08/13
     */
    public List<ProjectInfoDto> listPage(PageParam pageParam, ProjectInfoDto dto) {
        return this.lambdaQuery()
                .like(StringUtil.isNotBlank(dto.getResearchKey()),
                        ProjectInfoDto::getProjectName, dto.getResearchKey())
                .orderByDesc(ProjectInfoDto::getMtime)
                .last(StringUtil.format("LIMIT {}, {}", pageParam.getStartIndex(), pageParam.getPageSize()))
                .list();
    }

    /**
     * 分页查询项目信息
     *
     * @param dto 查询条件
     * @return {@link ProjectInfoDto}
     * @author darren
     * @since 2023/08/13
     */
    public Long listPageCount(ProjectInfoDto dto) {
        return this.lambdaQuery()
                .select(ProjectInfoDto::getId)
                .like(StringUtil.isNotBlank(dto.getResearchKey()),
                        ProjectInfoDto::getProjectName, dto.getResearchKey())
                .count().longValue();
    }

    /**
     * 验证项目是否重复
     *
     * @param param 项目信息
     * @return {@link Long}
     * @author darren
     * @since 2023/08/13
     */
    public Long countByRepeat(ProjectInfoDto param) {
        return this.lambdaQuery()
                .select(ProjectInfoDto::getId)
                .eq(StringUtil.isNotBlank(param.getProjectName()),
                        ProjectInfoDto::getProjectName, param.getProjectName())
                .eq(StringUtil.isNotBlank(param.getProjectAppKey()),
                        ProjectInfoDto::getProjectAppKey, param.getProjectAppKey())
                .ne(param.getIsUpdate(),
                        ProjectInfoDto::getId, param.getId())
                .count().longValue();
    }

    /**
     * 更新项目信息
     *
     * @param dto 项目信息
     * @author darren
     * @since 2023/08/13
     */
    public void updateInfo(ProjectInfoDto dto) {
        this.lambdaUpdate()
                .set(ProjectInfoDto::getProjectName, dto.getProjectName())
                .set(ProjectInfoDto::getProjectDesc, dto.getProjectDesc())
                .set(StringUtil.isNotBlank(dto.getProjectAppKey()),
                        ProjectInfoDto::getProjectAppKey, dto.getProjectAppKey())
                .set(ProjectInfoDto::getMtime, new Date())
                .set(ProjectInfoDto::getUpdater, dto.getUpdater())
                .eq(ProjectInfoDto::getId, dto.getId())
                .update();
    }

    /**
     * 根据id删除项目信息
     *
     * @param id      项目id
     * @param updater 更新者
     * @author darren
     * @since 2023/08/13
     */
    public void deleteById(Long id, Long updater) {
        this.lambdaUpdate()
                .set(ProjectInfoDto::getIsvalid, 0)
                .set(ProjectInfoDto::getUpdater, updater)
                .set(ProjectInfoDto::getMtime, new Date())
                .eq(ProjectInfoDto::getId, id)
                .update();
    }
}
