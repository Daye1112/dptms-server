package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysOrganizationDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserOrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户组织Dao
 *
 * @author luyuhao
 * @date 20/12/13 18:04
 */
@Mapper
@Repository
public interface SysUserOrganizationDao {

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link SysOrganizationDto}
     * @author luyuhao
     * @date 20/12/13 21:43
     */
    List<SysOrganizationDto> listUserAssigned(@Param("userId") Long userId);

    /**
     * 根据用户id删除记录
     *
     * @param userId  用户id
     * @param updater 更新者
     * @author luyuhao
     * @date 20/12/13 22:35
     */
    void deleteByUserId(@Param("userId") Long userId, @Param("updater") Long updater);

    /**
     * 批量插入用户组织信息
     *
     * @param list 用户组织信息
     * @author luyuhao
     * @date 20/12/13 22:42
     */
    void batchInsert(@Param("list") List<SysUserOrganizationEntity> list);
}
