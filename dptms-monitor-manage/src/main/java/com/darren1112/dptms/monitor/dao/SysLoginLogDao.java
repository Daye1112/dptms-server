package com.darren1112.dptms.monitor.dao;

import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录日志Dao
 *
 * @author luyuhao
 * @date 2021/02/06 20:40
 */
@Mapper
@Repository
public interface SysLoginLogDao {

    /**
     * 插入登录日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 21:04
     */
    void insert(SysLoginLogDto dto);

    /**
     * 分页查询登录日志
     *
     * @param pageParam 分页参数
     * @param dto       筛选条件
     * @return {@link SysLoginLogDto}
     * @author luyuhao
     * @date 2021/02/10 00:15
     */
    List<SysLoginLogDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") SysLoginLogDto dto);

    /**
     * 分页查询登录日志记录数
     *
     * @param dto 筛选条件
     * @return {@link Long}
     * @author luyuhao
     * @date 2021/02/10 00:19
     */
    Long listPageCount(@Param("dto") SysLoginLogDto dto);
}
