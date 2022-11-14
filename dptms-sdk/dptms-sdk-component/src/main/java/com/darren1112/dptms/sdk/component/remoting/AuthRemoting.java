package com.darren1112.dptms.sdk.component.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * auth模块rpc调用
 *
 * @author luyuhao
 * @since 2021/02/15 23:39
 */
@FeignClient("dptms-auth")
@RequestMapping(value = "/auth")
public interface AuthRemoting {

    /**
     * 接口访问统计
     *
     * @return {@link DruidStatDto}
     * @author luyuhao
     * @since 2021/02/17 20:45
     */
    @GetMapping("/druidStat/apiStat")
    JsonResult<List<DruidStatDto>> getApiStat();

    /**
     * 获取用户的权限
     *
     * @return {@link AuthUserDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @GetMapping("/activeUser/getNewInfo")
    JsonResult<AuthUserDto> getNewInfo();
}
