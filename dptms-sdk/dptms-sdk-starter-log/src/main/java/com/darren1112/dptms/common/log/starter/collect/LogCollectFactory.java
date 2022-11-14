package com.darren1112.dptms.common.log.starter.collect;

import com.darren1112.dptms.common.log.starter.collect.impl.FeignLogCollectServiceImpl;
import com.darren1112.dptms.common.log.starter.enums.LogCollectType;
import com.darren1112.dptms.sdk.component.remoting.MonitorManageRemoting;

/**
 * 日志收集器工厂
 *
 * @author luyuhao
 * @since 2021/7/30
 */
public class LogCollectFactory {

    private MonitorManageRemoting monitorManageRemoting;

    /**
     * 根据收集器类型枚举返回对应的采集器
     *
     * @param logCollectType 日志收集类型
     * @return {@link LogCollectService}
     * @author luyuhao
     * @since 2021/7/30
     */
    public LogCollectService create(LogCollectType logCollectType) {
        switch (logCollectType) {
            case FEIGN:
                return new FeignLogCollectServiceImpl(this.monitorManageRemoting);
            default:
                return new FeignLogCollectServiceImpl(this.monitorManageRemoting);
        }
    }

    public LogCollectFactory setMonitorManageRemoting(MonitorManageRemoting monitorManageRemoting) {
        this.monitorManageRemoting = monitorManageRemoting;
        return this;
    }
}
