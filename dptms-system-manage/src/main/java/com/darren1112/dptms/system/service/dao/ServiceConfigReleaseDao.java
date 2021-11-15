package com.darren1112.dptms.system.service.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置发布表Dao
 *
 * @author luyuhao
 * @since 2021/03/12 01:43
 */
@Mapper
@Repository
public interface ServiceConfigReleaseDao {

    /**
     * 分页查询发布列表
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ServiceConfigReleaseDto}
     * @author luyuhao
     * @since 2021/3/16 8:37
     */
    List<ServiceConfigReleaseDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") ServiceConfigReleaseDto dto);

    /**
     * 分页查询发布列表 - 总记录数
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigReleaseDto}
     * @author luyuhao
     * @since 2021/3/16 8:37
     */
    Long listPageCount(@Param("dto") ServiceConfigReleaseDto dto);

    /**
     * 统计重复版本记录数
     *
     * @param param 查询条件
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/3/16 8:52
     */
    Long countByRepeat(ServiceConfigReleaseDto param);

    /**
     * 插入发布信息
     *
     * @param dto 发布信息
     * @author luyuhao
     * @since 2021/3/16 8:55
     */
    void insert(ServiceConfigReleaseDto dto);

    /**
     * 删除发布信息
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/3/16 9:00
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
