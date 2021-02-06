package com.darren1112.dptms.monitor.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import com.darren1112.dptms.monitor.dao.SysLoginLogDao;
import com.darren1112.dptms.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志ServiceImpl
 *
 * @author luyuhao
 * @date 2021/02/06 20:44
 */
@Service
public class SysLoginLogServiceImpl extends BaseService implements SysLoginLogService {

    @Autowired
    private SysLoginLogDao sysLoginLogDao;

    /**
     * 插入登录日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 20:53
     */
    @Override
    public void insert(SysLoginLogDto dto) {
        sysLoginLogDao.insert(dto);
    }
}
