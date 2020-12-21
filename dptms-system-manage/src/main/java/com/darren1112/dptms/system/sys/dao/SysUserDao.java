package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 根据id查询用户
     *
     * @param id id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/11/30 23:12
     */
    SysUserDto getById(@Param("id") Long id);

    /**
     * 统计是否重复
     *
     * @param param 查询参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/22 01:06
     */
    Long countByRepeat(SysUserDto param);

    /**
     * 新增用户
     *
     * @param entity 用户信息
     * @author luyuhao
     * @date 20/12/22 01:08
     */
    void insert(SysUserEntity entity);

    /**
     * 分页查询用户信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/12/22 01:12
     */
    List<SysUserDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") SysUserDto dto);

    /**
     * 分页查询用户信息-记录数
     *
     * @param dto 查询条件
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/22 01:12
     */
    Long listPageCount(SysUserDto dto);

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long update(SysUserEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
