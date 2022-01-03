package com.darren1112.dptms.auth.dao;

import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Dao
 *
 * @author luyuhao
 * @since 2020/12/12 17:23
 */
@Mapper
@Repository
public interface SysMenuDao {

    /**
     * 获取用户的菜单
     *
     * @param userId 用户id
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    List<AuthMenuDto> listMenuByUserId(Long userId);

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/15 16:53
     */
    Long insert(AuthMenuEntity entity);

    /**
     * 查询是否重复
     *
     * @param param 查重参数
     * @return {@link Long 重复数量)
     * @author baojiazhong
     * @since 2020/12/16 11:08
     */
    Long countByRepeat(AuthMenuDto param);

    /**
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新着
     * @author baojiazhong
     * @since 2020/12/16 14:33
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/16 18:35
     */
    Long update(AuthMenuEntity entity);

    /**
     * 查询菜单list
     *
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 2021/01/03 23:44
     */
    List<AuthMenuDto> list();
}
