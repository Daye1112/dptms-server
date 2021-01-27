package com.darren1112.dptms.gateway.remoting;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
     * 获取用户的权限
     *
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    @GetMapping("/activeUser/listPermission")
    JsonResult<List<SysPermissionDto>> listPermission();
}
