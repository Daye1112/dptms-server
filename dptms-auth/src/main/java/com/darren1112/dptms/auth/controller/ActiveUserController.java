package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.AuthMenuService;
import com.darren1112.dptms.auth.service.AuthPermissionService;
import com.darren1112.dptms.auth.service.AuthUserService;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotBlankValidatorCallback;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
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
    private AuthMenuService authMenuService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthPermissionService authPermissionService;

    @Autowired
    private TokenStore tokenStore;

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
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(SecurityUserUtil.getActiveUser()));
    }

    /**
     * 获取用户最新信息
     *
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @GetMapping("/getNewInfo")
    @ApiOperation("获取用户最新信息")
    @Log(value = "获取用户最新信息", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<AuthUserDto>> getNewInfo() {
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        // 查询用户信息
        AuthUserDto result = authUserService.getById(activeUser.getId());
        // 查询用户权限
        if (result != null) {
            List<AuthPermissionDto> permissionList = authPermissionService.listByUserId(activeUser.getId());
            result.setPermissionList(permissionList);
        }
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 获取用户的菜单
     *
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @GetMapping("/listMenu")
    @ApiOperation("获取用户的菜单")
    @Log(value = "获取用户的菜单", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<List<AuthMenuDto>>> listMenu() {
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        List<AuthMenuDto> resultList = authMenuService.listMenuByUserId(activeUser.getId());
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
    public ResponseEntity<JsonResult> updateInfo(AuthUserEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getUsername(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_USERNAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置更新者
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setId(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        // 更新用户
        Long count = authUserService.update(entity);

        // 获取新的用户信息
        AuthUserDto dto = authUserService.getById(entity.getId());
        // 更新到现有用户中
        ActiveUser.convert(activeUser, dto);
        // 更新redis中的用户信息
        tokenStore.updateActiveUser(activeUser);
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
    public ResponseEntity<JsonResult> updatePassword(AuthUserDto dto) {
        ValidatorBuilder.build()
                .on(dto.getOldPassword(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_OLD_PASSWORD_NOT_NULL))
                .on(dto.getNewPassword(), new NotBlankValidatorCallback(AuthErrorCodeEnum.USER_NEW_PASSWORD_NOT_NULL))
                .doValidate().checkResult();
        // 设置更新者
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        dto.setId(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        // 更新密码
        authUserService.updatePassword(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
