package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Dao
 *
 * @author luyuhao
 * @date 20/12/09 23:43
 */
@Mapper
@Repository
public interface SysPermissionDao {

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link Long 权限id}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long insert(SysPermissionEntity entity);

    /**
     * 分页查询权限
     *
     * @param param     筛选参数
     * @param pageParam 分页参数
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    List<SysPermissionDto> listPage(@Param("pageParam") PageParam pageParam, @Param("param") SysPermissionDto param);

    /**
     * 分页查询权限-查询总数
     *
     * @param dto 筛选参数
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long listPageCount(SysPermissionDto dto);
}
