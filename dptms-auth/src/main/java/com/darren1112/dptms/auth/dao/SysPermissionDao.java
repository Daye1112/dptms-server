package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import org.apache.ibatis.annotations.Mapper;
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
     * 根据用户id查询权限list
     *
     * @param userId 用户id
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/17 21:30
     */
    List<SysPermissionDto> listByUserId(Long userId);
}
