package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色Dao
 *
 * @author luyuhao
 * @since 20/12/23 01:50
 */
@Mapper
@Repository
public interface AuthUserRoleDao {

    /**
     * 清空用户已分配的角色
     *
     * @param userId  用户id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/23 01:54
     */
    void deleteByUserId(@Param("userId") Long userId, @Param("updater") Long updater);

    /**
     * 批量插入
     *
     * @param list 插入list
     * @author luyuhao
     * @since 20/12/23 01:56
     */
    void batchInsert(@Param("list") List<AuthUserRoleEntity> list);

    /**
     * 查询用户关联的角色list
     *
     * @param userId 用户id
     * @return {@link AuthRoleDto}
     * @author luyuhao
     * @since 20/12/23 02:00
     */
    List<AuthRoleDto> listUserAssigned(Long userId);
}
