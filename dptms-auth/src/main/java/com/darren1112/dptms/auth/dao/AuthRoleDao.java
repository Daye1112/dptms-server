package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Dao
 *
 * @author luyuhao
 * @since 20/12/13 23:07
 */
@Mapper
@Repository
public interface AuthRoleDao {


    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long 角色id)
     * @author baojiazhong
     * @since 2020/12/19 1:19
     */
    Long insert(AuthRoleEntity entity);

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量)
     * @author baojiazhong
     * @since 2020/12/19 1:20
     */
    Long countByRepeat(AuthRoleDto param);

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/19 20:32
     */
    Long update(AuthRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link AuthRoleDto )
     * @author baojiazhong
     * @since 2020/12/19 23:19
     */
    List<AuthRoleDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") AuthRoleDto dto);

    /**
     * 分页查询角色-查询总数
     *
     * @param dto 筛选参数
     * @return {@link Long 查询总数)
     * @author baojiazhong
     * @since 2020/12/19 23:21
     */
    Long listPageCount(@Param("dto") AuthRoleDto dto);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/19 23:25
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
