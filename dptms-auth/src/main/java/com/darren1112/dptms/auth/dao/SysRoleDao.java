package com.darren1112.dptms.auth.dao;

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
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long 角色id)
     * @author baojiazhong
     * @date 2020/12/19 1:19
     */
    Long insert(SysRoleEntity entity);

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量)
     * @author baojiazhong
     * @date 2020/12/19 1:20
     */
    Long countByRepeat(SysRoleDto param);

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link Long)
     * @author baojiazhong
     * @date 2020/12/19 20:32
     */
    Long update(SysRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link SysRoleDto)
     * @author baojiazhong
     * @date 2020/12/19 23:19
     */
    List<SysRoleDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") SysRoleDto dto);

    /**
     * 分页查询角色-查询总数
     *
     * @param dto 筛选参数
     * @return {@link Long 查询总数)
     * @author baojiazhong
     * @date 2020/12/19 23:21
     */
    Long listPageCount(@Param("dto") SysRoleDto dto);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author baojiazhong
     * @date 2020/12/19 23:25
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
