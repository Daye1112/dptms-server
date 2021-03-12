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
}
