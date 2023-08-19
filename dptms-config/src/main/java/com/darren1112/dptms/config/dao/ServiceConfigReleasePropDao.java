package com.darren1112.dptms.config.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置发布属性表Dao
 *
 * @author darren
 * @since 2021/07/22
 */
@Mapper
@Repository
public interface ServiceConfigReleasePropDao extends BaseMapper<ServiceConfigReleasePropDto> {

    /**
     * 根据应用和环境查询最新的发布配置
     *
     * @param application 应用
     * @param profile     环境
     * @return {@link ServiceConfigReleasePropDto}
     * @author darren
     * @since 2021/7/23
     */
    List<ServiceConfigReleasePropDto> listLatest(@Param("application") String application, @Param("profile") String profile);

    /**
     * 根据应用、环境和版本信息查询发布配置
     *
     * @param application    应用
     * @param profile        环境
     * @param releaseVersion 发布版本
     * @return {@link ServiceConfigReleasePropDto}
     * @author darren
     * @since 2021/7/23
     */
    List<ServiceConfigReleasePropDto> listBy(@Param("application") String application, @Param("profile") String profile, @Param("releaseVersion") String releaseVersion);
}
