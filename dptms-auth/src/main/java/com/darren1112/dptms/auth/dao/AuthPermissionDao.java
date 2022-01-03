package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Dao
 *
 * @author luyuhao
 * @since 20/12/09 23:43
 */
@Mapper
@Repository
public interface AuthPermissionDao {

    /**
     * 根据用户id查询权限list
     *
     * @param userId 用户id
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2021/01/17 21:30
     */
    List<AuthPermissionDto> listByUserId(Long userId);

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link Long 权限id}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long insert(AuthPermissionEntity entity);

    /**
     * 分页查询权限
     *
     * @param param     筛选参数
     * @param pageParam 分页参数
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    List<AuthPermissionDto> listPage(@Param("pageParam") PageParam pageParam, @Param("param") AuthPermissionDto param);

    /**
     * 分页查询权限-查询总数
     *
     * @param dto 筛选参数
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long listPageCount(AuthPermissionDto dto);

    /**
     * 更新权限信息
     *
     * @param entity 权限参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long update(AuthPermissionEntity entity);

    /**
     * 根据筛选参数查询list
     *
     * @param entity 筛选参数
     * @return {@link AuthPermissionEntity}
     * @author luyuhao
     * @since 2020/12/12 11:11
     */
    List<AuthPermissionEntity> listBy(AuthPermissionEntity entity);

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量}
     * @author luyuhao
     * @since 2020/12/12 11:14
     */
    Long countByRepeat(AuthPermissionDto param);

    /**
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/12 20:44
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 查询权限组list
     *
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2020/12/28 01:10
     */
    List<AuthPermissionDto> listGroup();
}
