package com.darren1112.dptms.monitor.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志Dao
 *
 * @author luyuhao
 * @date 2021/02/06 20:40
 */
@Mapper
@Repository
public interface SysOperateLogDao {

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 21:08
     */
    void insert(SysOperateLogDto dto);
}
