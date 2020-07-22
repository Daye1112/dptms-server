package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.spi.auth.dto.SysUserDto;
import com.darren1112.dptms.spi.auth.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统用户Dao
 *
 * @author luyuhao
 * @date 20/07/23 02:22
 */
@Mapper
@Repository
public interface SysUserDao {

    /**
     * 插入数据
     *
     * @param sysUserEntity 用户实体类
     * @author luyuhao
     * @date 20/07/23 02:26
     */
    void insert(SysUserEntity sysUserEntity);

    /**
     * 更新数据
     *
     * @param sysUserDto 用户dto
     * @author luyuhao
     * @date 20/07/23 02:26
     */
    void update(SysUserDto sysUserDto);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @date 20/07/23 02:49
     */
    SysUserDto getByUsername(@Param("username") String username);

}
