package com.darren1112.dptms.gateway.remoting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * auth服务rpc调用
 *
 * @author luyuhao
 * @since 2020/11/28 11:13
 */
@FeignClient("dptms-auth")
@RequestMapping(value = "/auth")
public interface AuthRemoting {
}
