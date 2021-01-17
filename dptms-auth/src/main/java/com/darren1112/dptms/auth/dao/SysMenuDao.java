package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Dao
 *
 * @author luyuhao
 * @date 2020/12/12 17:23
 */
@Mapper
@Repository
public interface SysMenuDao {

    /**
     * 获取用户的菜单
     *
     * @param userId 用户id
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    List<SysMenuDto> listMenuByUserId(Long userId);
}
