package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 系统用户Controller
 *
 * @author luyuhao
 * @date 2020/07/25 02:14
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户信息
     *
     * @param principal security用户对象
     * @return Principal
     * @author luyuhao
     * @date 20/08/09 03:36
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public ResponseEntity<JsonResult<Principal>> currentUser(Principal principal) {
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(principal));
    }
}
