package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;

/**
 * 菜单Service
 *
 * @author luyuhao
 * @since 2020/12/12 17:25
 */
public interface SysMenuService {

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long 菜单id)
     * @author baojiazhong
     * @date 2020/12/16 11:00
     */
    Long insert(SysMenuEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新者
     * @author baojiazhong
     * @date 2020/12/16 14:38
     */
    void deleteById(Long id, Long updater);

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @date 2020/12/16 15:29
     */
    Long update(SysMenuEntity entity);

    /**
     * 查询菜单树
     *
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/03 23:29
     */
    SysMenuDto listTree();
}
