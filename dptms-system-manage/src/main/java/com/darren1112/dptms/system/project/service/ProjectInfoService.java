package com.darren1112.dptms.system.project.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.project.dto.ProjectInfoDto;

/**
 * 项目信息Service
 *
 * @author darren
 * @since 2023/08/13
 */
public interface ProjectInfoService {

    /**
     * 分页查询项目信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ProjectInfoDto}
     * @author darren
     * @since 2023/08/13
     */
    PageBean<ProjectInfoDto> listPage(PageParam pageParam, ProjectInfoDto dto);

    /**
     * 新增项目信息
     *
     * @param dto 项目信息
     * @return {@link Long}
     * @author darren
     * @since 2023/08/13
     */
    Long insert(ProjectInfoDto dto);

    /**
     * 更新项目信息
     *
     * @param dto 项目信息
     * @author darren
     * @since 2023/08/13
     */
    void update(ProjectInfoDto dto);

    /**
     * 根据id删除项目信息
     *
     * @param id      数据id
     * @param updater 更新者
     * @author darren
     * @since 2023/08/13
     */
    void deleteById(Long id, Long updater);
}
