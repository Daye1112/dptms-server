package com.darren1112.dptms.common.log.starter.collect.impl;

import com.darren1112.dptms.common.log.starter.collect.LogCollectService;
import com.darren1112.dptms.common.log.starter.remoting.MonitorManageRemoting;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luyuhao
 * @date 2021/02/07 00:32
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
     * @date 2021/02/07 00:32
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
}
