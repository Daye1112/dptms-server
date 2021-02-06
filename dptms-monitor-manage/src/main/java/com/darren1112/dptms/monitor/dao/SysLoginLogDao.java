package com.darren1112.dptms.monitor.dao;

import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
