package com.darren1112.dptms.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleMenuDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色菜单Dao
 *
 * @author darren
 * @since 20/12/13 23:07
 */
@Mapper
@Repository
public interface AuthRoleMenuDao extends BaseMapper<AuthRoleMenuDto> {

    /**
     * 查询角色关联的菜单list
     *
     * @param roleId 角色id
     * @return {@link AuthMenuDto}
     * @author darren
     * @since 20/12/13 21:43
     */
    List<AuthMenuDto> listRoleAssigned(@Param("roleId") Long roleId);

    /**
     * 清空角色已分配的组织
     *
     * @param roleId  角色id
     * @param updater 更新者
     * @author darren
     * @since 20/12/21 01:02
     */
    void deleteByRoleId(@Param("roleId") Long roleId, @Param("updater") Long updater);

    /**
     * 批量插入
     *
     * @param list 分配信息
     * @author darren
     * @since 20/12/21 01:04
     */
    void batchInsert(@Param("list") List<AuthRoleMenuEntity> list);
}
