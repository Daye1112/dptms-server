package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @since 2020/07/23 02:42
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @since 20/07/23 02:58
     */
    SysUserDto getByUsername(String username);

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
     * @return {@link SysUserDto}
     * @author luyuhao
     * @since 20/11/30 23:12
     */
    SysUserDto getById(Long id);

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long insert(SysUserEntity entity);

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysUserDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    PageBean<SysUserDto> listPage(PageParam pageParam, SysUserDto dto);

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long update(SysUserEntity entity);

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
    void updateLock(SysUserEntity entity);

    /**
     * 根据用户id查询用户信息和权限list
     *
     * @param id 用户id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @since 2021/01/31 19:39
     */
    SysUserDto getUserInfoAndPermissionByUserId(Long id);

    /**
     * 更新密码
     *
     * @param dto 用户信息
     * @author luyuhao
     * @since 2021/11/24
     */
    void updatePassword(SysUserDto dto);
}
