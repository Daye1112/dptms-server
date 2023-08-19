package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleEntity;

/**
 * 角色Service
 *
 * @author darren
 * @since 2020/12/13 23:09
 */
public interface AuthRoleService {

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long 角色id)
     * @author baojiazhong
     * @since 2020/12/19 1:07
     */
    Long insert(AuthRoleEntity entity);

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/19 20:05
     */
    Long update(AuthRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link AuthRoleDto )
     * @author baojiazhong
     * @since 2020/12/19 20:33
     */
    PageBean<AuthRoleDto> listPage(PageParam pageParam, AuthRoleDto dto);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/19 20:35
     */
    void deleteById(Long id, Long updater);
}
