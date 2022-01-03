package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @since 2020/07/23 02:42
 */
public interface AuthUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @since 20/07/23 02:58
     */
    AuthUserDto getByUsername(String username);

    /**
     * 更新登录时间
     *
     * @param id 用户id
     * @author luyuhao
     * @since 20/12/09 01:02
     */
    void updateLoginTime(Long id);

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/11/30 23:12
     */
    AuthUserDto getById(Long id);

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long insert(AuthUserEntity entity);

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    PageBean<AuthUserDto> listPage(PageParam pageParam, AuthUserDto dto);

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
    void deleteById(Long id, Long updater);

    /**
     * 更新用户锁定状态
     *
     * @param entity 更新状态
     * @author luyuhao
     * @since 2021/01/14 00:19
     */
    void updateLock(AuthUserEntity entity);

    /**
     * 根据用户id查询用户信息和权限list
     *
     * @param id 用户id
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 2021/01/31 19:39
     */
    AuthUserDto getUserInfoAndPermissionByUserId(Long id);

    /**
     * 更新密码
     *
     * @param dto 用户信息
     * @author luyuhao
     * @since 2021/11/24
     */
    void updatePassword(AuthUserDto dto);
}
