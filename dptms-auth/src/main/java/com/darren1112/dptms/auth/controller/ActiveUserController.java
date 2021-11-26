package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.SysMenuService;
import com.darren1112.dptms.auth.service.SysUserService;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotBlankValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 认证管理Controller
 *
 * @author luyuhao
 * @since 2020/07/20 00:06
 */
@Slf4j
@Api(tags = "认证管理")
@RestController
@RequestMapping(value = "/activeUser")
public class ActiveUserController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DptmsTokenStore dptmsTokenStore;

    /**
     * 获取用户信息
     *
     * @return {@link ActiveUser}
     * @author luyuhao
     * @since 2021/01/17 19:24
     */
    @GetMapping("/getInfo")
    @ApiOperation("获取用户信息")
    @Log(value = "获取用户信息", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<ActiveUser>> getInfo() {
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(DptmsSecurityUtil.get()));
    }

    /**
     * 获取用户最新信息
     *
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @GetMapping("/getNewInfo")
    @ApiOperation("获取用户最新信息")
    @Log(value = "获取用户最新信息", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<SysUserDto>> getNewInfo() {
        ActiveUser activeUser = DptmsSecurityUtil.get();
        SysUserDto result = sysUserService.getUserInfoAndPermissionByUserId(activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 获取用户的菜单
     *
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @GetMapping("/listMenu")
    @ApiOperation("获取用户的菜单")
    @Log(value = "获取用户的菜单", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<List<SysMenuDto>>> listMenu() {
        ActiveUser activeUser = DptmsSecurityUtil.get();
        List<SysMenuDto> resultList = sysMenuService.listMenuByUserId(activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }

    /**
     * 更新个人信息
     *
     * @param entity 用户信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/11/24
     */
    @PostMapping("/updateInfo")
    @ApiOperation("更新个人信息")
    @Log(value = "更新个人信息", logLevel = LogLevel.INFO, businessType = BusinessType.UPDATE)
    public ResponseEntity<JsonResult> updateInfo(SysUserEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getUsername(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_USERNAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置更新者
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setId(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        // 更新用户
        Long count = sysUserService.update(entity);

        // 获取新的用户信息
        SysUserDto dto = sysUserService.getById(entity.getId());
        // 更新到现有用户中
        ActiveUser.convert(activeUser, dto);
        // 更新redis中的用户信息
        dptmsTokenStore.updateActiveUser(activeUser);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 更新密码
     *
     * @param dto 用户信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/11/24
     */
    @PostMapping("/updatePassword")
    @ApiOperation("更新密码")
    @Log(value = "更新密码", logLevel = LogLevel.INFO, businessType = BusinessType.UPDATE)
    public ResponseEntity<JsonResult> updatePassword(SysUserDto dto) {
        ValidatorBuilder.build()
                .on(dto.getOldPassword(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_OLD_PASSWORD_NOT_NULL))
                .on(dto.getNewPassword(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_NEW_PASSWORD_NOT_NULL))
                .doValidate().checkResult();
        // 设置更新者
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setId(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        // 更新密码
        sysUserService.updatePassword(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
