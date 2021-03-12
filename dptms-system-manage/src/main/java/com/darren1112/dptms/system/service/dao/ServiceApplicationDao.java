package com.darren1112.dptms.system.service.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用表Dao
 *
 * @author luyuhao
 * @date 2020/12/12 17:23
 */
@Mapper
@Repository
public interface ServiceApplicationDao {

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    List<ServiceApplicationDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") ServiceApplicationDto dto);

    /**
     * 分页查询服务应用-记录数
     *
     * @param dto 筛选参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    Long listPageCount(@Param("dto") ServiceApplicationDto dto);

    /**
     * 统计重复的服务应用
     *
     * @param param 查询条件
     * @return {@link Long 重复数量}
     * @author luyuhao
     * @date 2021/03/12 23:32
     */
    Long countByRepeat(ServiceApplicationDto param);

    /**
     * 新增服务应用
     *
     * @param dto 服务信息
     * @author luyuhao
     * @date 2021/03/12 23:35
     */
    void insert(ServiceApplicationDto dto);

    /**
     * 更新服务应用
     *
     * @param dto 服务信息
     * @author luyuhao
     * @date 2021/03/12 23:43
     */
    void update(ServiceApplicationDto dto);

    /**
     * 根据id删除服务
     *
     * @param id      服务id
     * @param updater 更新者
     * @author luyuhao
     * @date 2021/03/13 00:49
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);
}
