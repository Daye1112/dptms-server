package com.darren1112.dptms.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuPermissionDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuPermissionEntity;
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
public interface AuthMenuPermissionDao extends BaseMapper<AuthMenuPermissionDto> {

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto )
     * @author baojiazhong
     * @since 2020/12/22 22:48
     */
    List<AuthPermissionDto> listMenuAssigned(@Param("menuId") Long menuId);

    /**
     * 根据菜单id删除记录
     *
     * @param menuId  菜单id
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/22 23:27
     */
    void deleteByMenuId(@Param("menuId") Long menuId, @Param("updater") Long updater);

    /**
     * 批量插入菜单权限信息
     *
     * @param list 菜单权限信息
     * @author baojiazhong
     * @since 2020/12/22 23:27
     */
    void batchInsert(@Param("list") List<AuthMenuPermissionEntity> list);

    /**
     * 根据菜单id查询权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2021/01/04 23:53
     */
    List<AuthPermissionDto> listByMenuId(@Param("menuId") Long menuId);
}
