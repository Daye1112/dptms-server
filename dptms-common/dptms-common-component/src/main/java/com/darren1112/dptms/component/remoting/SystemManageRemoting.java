package com.darren1112.dptms.component.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * system manage 模块rpc调用
 *
 * @author luyuhao
 * @date 2021/02/15 23:40
 */
@FeignClient("dptms-system-manage")
@RequestMapping(value = "/system-manage")
public interface SystemManageRemoting {

    /**
     * 接口访问统计
     *
     * @return {@link DruidStatDto}
     * @author luyuhao
     * @date 2021/02/17 20:45
     */
    @GetMapping("/druidStat/apiStat")
    JsonResult<List<DruidStatDto>> getApiStat();
}
