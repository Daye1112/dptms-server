package com.darren1112.dptms.system.sys.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Dao
 *
 * @author luyuhao
 * @date 2020/12/12 17:23
 */
@Mapper
@Repository
public interface SysMenuDao {

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @date 2020/12/15 16:53
     */
    Long insert(SysMenuEntity entity);

    /**
     * 分页查询菜单
     *
     * @param param     筛选参数
     * @param pageParam 分页参数
     * @return {@link List<SysMenuDto>)
     * @author baojiazhong
     * @date 2020/12/15 18:11
     */
    List<SysMenuDto> listPage(@Param("pageParam")PageParam pageParam, @Param("param") SysMenuDto param);

    /**
     * 分页查询菜单-查询总数
     *
     * @param dto 筛选参数
     * @return {@link Long 查询总数)
     * @author baojiazhong
     * @date 2020/12/16 15:02
     */
    Long listPageCount(SysMenuDto dto);

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量)
     * @author baojiazhong
     * @date 2020/12/16 11:08
     */
    Long countByRepeat(SysMenuDto param);

    /**
     * 根据id删除记录
     *
     * @param id        id
     * @param updater   更新着
     * @author baojiazhong
     * @date 2020/12/16 14:33
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @date 2020/12/16 18:35
     */
    Long update(SysMenuEntity entity);
}