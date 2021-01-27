package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.service.SysMenuService;
import com.darren1112.dptms.auth.service.SysPermissionService;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 认证管理Controller
 *
 * @author luyuhao
 * @date 2020/07/20 00:06
 */
@Slf4j
@Api(tags = "认证管理", description = "认证管理接口")
@RestController
@RequestMapping(value = "/activeUser")
public class ActiveUserController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取用户信息
     *
     * @return {@link ActiveUser}
     * @author luyuhao
     * @date 2021/01/17 19:24
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/getInfo")
    public ResponseEntity<JsonResult<ActiveUser>> getInfo() {
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(DptmsSecurityUtil.get()));
    }

    /**
     * 获取用户的权限
     *
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    @ApiOperation("获取用户的权限")
    @GetMapping("/listPermission")
    public ResponseEntity<JsonResult<List<SysPermissionDto>>> listPermission() {
        ActiveUser activeUser = DptmsSecurityUtil.get();
        List<SysPermissionDto> resultList = sysPermissionService.listByUserId(activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }

    /**
     * 获取用户的菜单
     *
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 2021/01/17 19:34
     */
    @ApiOperation("获取用户的菜单")
    @GetMapping("/listMenu")
    public ResponseEntity<JsonResult<List<SysMenuDto>>> listMenu() {
        ActiveUser activeUser = DptmsSecurityUtil.get();
        List<SysMenuDto> resultList = sysMenuService.listMenuByUserId(activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }
}
