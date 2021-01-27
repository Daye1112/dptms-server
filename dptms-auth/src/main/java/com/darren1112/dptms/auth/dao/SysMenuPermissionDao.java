package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单权限Dao
 *
 * @author baojiazhong
 * @since 2020/12/22 22:45
 */
@Mapper
@Repository
public interface SysMenuPermissionDao {

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto)
     * @author baojiazhong
     * @date 2020/12/22 22:48
     */
    List<SysPermissionDto> listMenuAssigned(@Param("menuId") Long menuId);

    /**
     * 根据菜单id删除记录
     *
     * @param menuId  菜单id
     * @param updater 更新者
     * @author baojiazhong
     * @date 2020/12/22 23:27
     */
    void deleteByMenuId(@Param("menuId") Long menuId, @Param("updater") Long updater);

    /**
     * 批量插入菜单权限信息
     *
     * @param list 菜单权限信息
     * @author baojiazhong
     * @date 2020/12/22 23:27
     */
    void batchInsert(@Param("list") List<SysMenuPermissionEntity> list);

    /**
     * 根据菜单id查询权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/04 23:53
     */
    List<SysPermissionDto> listByMenuId(@Param("menuId") Long menuId);
}
