package com.darren1112.dptms.system.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置环境表Dao
 *
 * @author darren
 * @since 2021/03/12 01:43
 */
@Mapper
@Repository
public interface ServiceConfigProfileDao extends BaseMapper<ServiceConfigProfileDto> {

    /**
     * 查询服务应用list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigProfileDto}
     * @author darren
     * @since 2021/03/13 01:50
     */
    List<ServiceConfigProfileDto> list(ServiceConfigProfileDto dto);

    /**
     * 验证配置环境是否重复
     *
     * @param param 参数
     * @return {@link Long}
     * @author darren
     * @since 2021/03/14 02:05
     */
    Long countByRepeat(ServiceConfigProfileDto param);

    /**
     * 更新配置环境
     *
     * @param dto 配置环境信息
     * @author darren
     * @since 2021/03/14 02:14
     */
    void update(ServiceConfigProfileDto dto);

    /**
     * 根据id删除配置环境
     *
     * @param id      配置环境id
     * @param updater 更新者id
     * @author darren
     * @since 2021/03/14 02:18
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
