package com.darren1112.dptms.system.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置环境属性表Dao
 *
 * @author darren
 * @since 2021/03/12 01:43
 */
@Mapper
@Repository
public interface ServiceConfigProfilePropDao extends BaseMapper<ServiceConfigProfilePropDto> {

    /**
     * 查询配置环境属性list
     *
     * @param dto 配置环境属性
     * @return {@link ServiceConfigProfilePropDto}
     * @author darren
     * @since 2021/03/14 22:57
     */
    List<ServiceConfigProfilePropDto> list(ServiceConfigProfilePropDto dto);

    /**
     * 根据条件筛选匹配参数-记录数
     *
     * @param param 查询信息
     * @return {@link Long}
     * @author darren
     * @since 2021/03/14 23:04
     */
    Long countByRepeat(ServiceConfigProfilePropDto param);

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author darren
     * @since 2021/03/14 23:08
     */
    void update(ServiceConfigProfilePropDto dto);

    /**
     * 根据id删除配置环境属性
     *
     * @param id      id
     * @param updater 更新者
     * @author darren
     * @since 2021/03/14 23:11
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 批量新增环境属性
     *
     * @param propList 环境属性集合
     * @author darren
     * @since 2021/7/21
     */
    void batchInsert(List<ServiceConfigProfilePropDto> propList);
}
