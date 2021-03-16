package com.darren1112.dptms.system.service.dao;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置环境属性表Dao
 *
 * @author luyuhao
 * @since 2021/03/12 01:43
 */
@Mapper
@Repository
public interface ServiceConfigProfilePropDao {

    /**
     * 查询配置环境属性list
     *
     * @param dto 配置环境属性
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/03/14 22:57
     */
    List<ServiceConfigProfilePropDto> list(ServiceConfigProfilePropDto dto);

    /**
     * 根据条件筛选匹配参数-记录数
     *
     * @param param 查询信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 23:04
     */
    Long countByRepeat(ServiceConfigProfilePropDto param);


    /**
     * 插入配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author luyuhao
     * @since 2021/03/14 23:01
     */
    void insert(ServiceConfigProfilePropDto dto);

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author luyuhao
     * @since 2021/03/14 23:08
     */
    void update(ServiceConfigProfilePropDto dto);

    /**
     * 根据id删除配置环境属性
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/03/14 23:11
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
