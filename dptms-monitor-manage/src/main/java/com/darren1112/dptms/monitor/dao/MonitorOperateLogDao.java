package com.darren1112.dptms.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorOperateLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志Dao
 *
 * @author luyuhao
 * @since 2021/02/06 20:40
 */
@Mapper
@Repository
public interface MonitorOperateLogDao extends BaseMapper<MonitorOperateLogDto> {

    /**
     * 分页查询操作日志
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link MonitorOperateLogDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    List<MonitorOperateLogDto> listPage(@Param("pageParam") PageParam pageParam, @Param("dto") MonitorOperateLogDto dto);

    /**
     * 分页查询操作日志-记录数
     *
     * @param dto 筛选参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long listPageCount(@Param("dto") MonitorOperateLogDto dto);
}
