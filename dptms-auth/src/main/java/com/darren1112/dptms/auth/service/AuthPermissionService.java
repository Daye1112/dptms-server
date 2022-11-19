package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthPermissionEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;

import java.util.List;

/**
 * 权限Service
 *
 * @author luyuhao
 * @since 2020/12/09 23:43
 */
public interface AuthPermissionService {

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
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    PageBean<AuthPermissionDto> listPage(PageParam pageParam, AuthPermissionDto dto);

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
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/12 20:44
     */
    void deleteById(Long id, Long updater);

    /**
     * 查询权限组list
     *
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2020/12/28 01:10
     */
    List<AuthPermissionDto> listGroup();

    /**
     * 根据用户id获取权限集合
     *
     * @param userId 用户id
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2022/11/19
     */
    List<AuthPermissionDto> listByUserId(Long userId);
}
