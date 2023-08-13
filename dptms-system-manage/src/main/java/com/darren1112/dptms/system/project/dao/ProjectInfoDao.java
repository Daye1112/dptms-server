package com.darren1112.dptms.system.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.project.dto.ProjectInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 项目信息Dao
 *
 * @author darren
 * @since 2023/08/13
 */
@Mapper
@Repository
public interface ProjectInfoDao extends BaseMapper<ProjectInfoDto> {
}
