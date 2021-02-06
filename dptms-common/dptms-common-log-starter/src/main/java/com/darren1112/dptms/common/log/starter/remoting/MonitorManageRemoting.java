package com.darren1112.dptms.common.log.starter.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 监控管理服务rpc调用
 *
 * @author luyuhao
 * @date 2021/02/06 22:23
 */
@FeignClient("dptms-monitor-manage")
@RequestMapping(value = "/monitor-manage")
public interface MonitorManageRemoting {

    /**
     * 插入登录日志信息
     *
     * @param dto 日志参数
     * @return {@link JsonResult)
     * @author luyuhao
     * @date 2021/02/06 20:50
     */
    @PostMapping("/loginLog/insert")
    JsonResult insert(SysLoginLogDto dto);

    /**
     * 插入操作日志信息
     *
     * @param dto 日志参数
     * @return {@link JsonResult)
     * @author luyuhao
     * @date 2021/02/06 20:50
     */
    @PostMapping("/operateLog/insert")
    JsonResult insert(SysOperateLogDto dto);
}
