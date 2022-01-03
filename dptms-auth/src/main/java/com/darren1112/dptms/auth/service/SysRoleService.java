package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.SysRoleEntity;

/**
 * 角色Service
 *
 * @author luyuhao
 * @since 2020/12/13 23:09
 */
public interface SysRoleService {

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long 角色id)
     * @author baojiazhong
     * @since 2020/12/19 1:07
     */
    Long insert(SysRoleEntity entity);

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/19 20:05
     */
    Long update(SysRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link SysRoleDto)
     * @author baojiazhong
     * @since 2020/12/19 20:33
     */
    PageBean<SysRoleDto> listPage(PageParam pageParam, SysRoleDto dto);

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
