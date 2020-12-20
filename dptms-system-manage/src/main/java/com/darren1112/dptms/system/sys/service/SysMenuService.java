package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
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
     * @author baojiazhong
     * @return {@link Long 菜单id)
     * @date 2020/12/16 11:00
     */
    Long insert(SysMenuEntity entity);

    /**
     * 分页查询菜单
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysMenuDto)
     * @author baojiazhong
     * @date 2020/12/16 15:09
     */
    PageBean<SysMenuDto> listPage(PageParam pageParam, SysMenuDto dto);

    /**
     * 根据id删除记录
     *
     * @param id        id
     * @param updater   更新者
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
}
