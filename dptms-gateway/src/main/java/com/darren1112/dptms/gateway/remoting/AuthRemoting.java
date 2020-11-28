package com.darren1112.dptms.gateway.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * auth服务rpc调用
 *
 * @author luyuhao
 * @since 2020/11/28 11:13
 */
@FeignClient("dptms-auth")
@RequestMapping(value = "/auth")
public interface AuthRemoting {

    /**
     * ------------------------ AuthController ------------------------
     * 刷新accessToken
     *
     * @param refreshToken refreshToken
     * @return newAccessToken
     * @author luyuhao
     * @date 2020/11/28 11:16
     */
    @PostMapping("/refreshAccessToken")
    JsonResult<String> refreshAccessToken(@RequestParam("refreshToken") String refreshToken);
}
