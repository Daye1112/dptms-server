package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserOrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户组织Dao
 *
 * @author luyuhao
 * @since 20/12/13 18:04
 */
@Mapper
@Repository
public interface SysUserOrganizationDao {

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link AuthOrganizationDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    List<AuthOrganizationDto> listUserAssigned(@Param("userId") Long userId);

    /**
     * 根据用户id删除记录
     *
     * @param userId  用户id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/13 22:35
     */
    void deleteByUserId(@Param("userId") Long userId, @Param("updater") Long updater);

    /**
     * 批量插入用户组织信息
     *
     * @param list 用户组织信息
     * @author luyuhao
     * @since 20/12/13 22:42
     */
    void batchInsert(@Param("list") List<AuthUserOrganizationEntity> list);
}
