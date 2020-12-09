package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
