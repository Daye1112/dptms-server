package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Dao
 *
 * @author luyuhao
 * @date 20/12/13 23:07
 */
@Mapper
@Repository
public interface SysRoleDao {

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量}
     * @author luyuhao
     * @date 2020/12/12 11:14
     */
    Long countByRepeat(SysRoleEntity param);

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    void insert(SysRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysRoleDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    List<SysRoleDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") SysRoleDto dto);

    /**
     * 分页查询角色-总记录数
     *
     * @param dto       筛选参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long listPageCount(SysRoleDto dto);

    /**
     * 更新角色信息
     *
     * @param entity 权限参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long update(SysRoleEntity entity);

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
