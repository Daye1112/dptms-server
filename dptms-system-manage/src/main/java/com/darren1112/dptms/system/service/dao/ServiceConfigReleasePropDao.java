package com.darren1112.dptms.system.service.dao;

import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置发布属性表Dao
 *
 * @author luyuhao
 * @since 2021/03/12 01:44
 */
@Mapper
@Repository
public interface ServiceConfigReleasePropDao {

    /**
     * 查询配置发布属性list
     *
     * @param dto 查询条件
     * @return {@link ServiceConfigReleasePropDto}
     * @author luyuhao
     * @since 2021/3/17 8:58
     */
    List<ServiceConfigReleasePropDto> list(ServiceConfigReleasePropDto dto);
}
