package com.darren1112.dptms.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户Dao
 *
 * @author luyuhao
 * @since 20/07/23 02:22
 */
@Mapper
@Repository
public interface AuthUserDao extends BaseMapper<AuthUserDto> {

    /**
     * 插入数据
     *
     * @param authUserEntity 用户实体类
     * @author luyuhao
     * @since 20/07/23 02:26
     */
    void insert(AuthUserEntity authUserEntity);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @since 20/07/23 02:49
     */
    AuthUserDto getByUsername(@Param("username") String username);

    /**
     * 更新登录时间
     *
     * @param id 用户id
     * @author luyuhao
     * @since 20/12/09 01:02
     */
    void updateLastLoginTime(@Param("id") Long id);

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/11/30 23:12
     */
    AuthUserDto getById(@Param("id") Long id);

    /**
     * 统计是否重复
     *
     * @param param 查询参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/22 01:06
     */
    Long countByRepeat(AuthUserDto param);

    /**
     * 分页查询用户信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/12/22 01:12
     */
    List<AuthUserDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") AuthUserDto dto);

    /**
     * 分页查询用户信息-记录数
     *
     * @param dto 查询条件
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/22 01:12
     */
    Long listPageCount(AuthUserDto dto);

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long update(AuthUserEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 更新用户锁定状态
     *
     * @param entity 更新状态
     * @author luyuhao
     * @since 2021/01/14 00:19
     */
    void updateLock(AuthUserEntity entity);

    /**
     * 更新用户密码
     *
     * @param dto 用户信息
     * @author luyuhao
     * @since 2021/11/24
     */
    void updatePassword(AuthUserDto dto);
}
