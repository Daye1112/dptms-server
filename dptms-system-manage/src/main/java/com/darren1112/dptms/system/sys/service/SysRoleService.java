package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;

/**
 * 角色Service
 *
 * @author luyuhao
 * @date 2020/12/13 23:09
 */
public interface SysRoleService {

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long insert(SysRoleEntity entity);

    /**
     * 分页查询角色
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysRoleDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    PageBean<SysRoleDto> listPage(PageParam pageParam, SysRoleDto dto);

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
    void deleteById(Long id, Long updater);
}
