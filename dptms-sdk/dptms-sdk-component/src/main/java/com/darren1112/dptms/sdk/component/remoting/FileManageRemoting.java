package com.darren1112.dptms.sdk.component.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 文件中心远程调用
 *
 * @author darren
 * @since 2022/01/03
 */
@FeignClient("dptms-file-manage")
@RequestMapping(value = "/file-manage")
public interface FileManageRemoting {

    /**
     * 接口访问统计
     *
     * @return {@link DruidStatDto}
     * @author darren
     * @since 2021/02/17 20:45
     */
    @GetMapping("/druidStat/apiStat")
    JsonResult<List<DruidStatDto>> getApiStat();
}
