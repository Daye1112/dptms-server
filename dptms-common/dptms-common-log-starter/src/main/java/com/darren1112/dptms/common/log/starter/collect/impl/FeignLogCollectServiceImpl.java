package com.darren1112.dptms.common.log.starter.collect.impl;

import com.darren1112.dptms.common.log.starter.collect.LogCollectService;
import com.darren1112.dptms.component.remoting.MonitorManageRemoting;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import lombok.extern.slf4j.Slf4j;

/**
 * feign-日志收集
 *
 * @author luyuhao
 * @since 2021/02/07 00:32
 */
@Slf4j
public class FeignLogCollectServiceImpl implements LogCollectService {

    private MonitorManageRemoting monitorManageRemoting;

    public FeignLogCollectServiceImpl(MonitorManageRemoting monitorManageRemoting) {
        this.monitorManageRemoting = monitorManageRemoting;
    }

    /**
     * 操作日志收集
     *
     * @param dto 日志信息
     * @author luyuhao
     * @since 2021/02/07 00:32
     */
    @Override
    public void operateLogCollect(SysOperateLogDto dto) {
        try {
            monitorManageRemoting.insert(dto);
            log.info("日志采集成功, 用户: {}, 操作内容: {}", dto.getUsername(), dto.getContent());
        } catch (Exception e) {
            log.error("监控系统未启动");
        }
    }

    /**
     * 插入登录日志信息
     *
     * @param dto 日志参数
     * @author luyuhao
     * @since 2021/02/06 20:50
     */
    @Override
    public void loginLogCollect(SysLoginLogDto dto) {
        try {
            monitorManageRemoting.insert(dto);
            log.info("登录日志采集成功, 用户: {}, 登录ip: {}", dto.getUsername(), dto.getIp());
        } catch (Exception e) {
            log.error("监控系统未启动");
        }
    }
}
