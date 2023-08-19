package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.AuthUserOrganizationService;
import com.darren1112.dptms.auth.service.AuthUserRoleService;
import com.darren1112.dptms.auth.service.AuthUserService;
import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.constants.AccountConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.Md5Util;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户Controller
 *
 * @author darren
 * @since 2020/07/25 02:14
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class AuthUserController extends BaseController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthUserOrganizationService authUserOrganizationService;

    @Autowired
    private AuthUserRoleService authUserRoleService;

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link AuthUserDto}
     * @author darren
     * @since 20/11/30 23:12
     */
    @Log(value = "根据id查询用户", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("根据id查询用户")
    @GetMapping("/getById")
    public ResponseEntity<JsonResult<AuthUserDto>> getById(Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        AuthUserDto result = authUserService.getById(id);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 分配组织
     *
     * @param userId 用户id
     * @param orgIds 组织ids，逗号分隔
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/13 22:10
     */
    @Log(value = "分配用户组织", businessType = BusinessType.UPDATE)
    @ApiOperation("分配组织")
    @GetMapping("/assignedOrg")
    public ResponseEntity<JsonResult> assignedOrg(@RequestParam(value = "userId", required = false) Long userId,
                                                  @RequestParam(value = "orgIds", required = false) String orgIds) {
        ValidatorBuilder.build()
                .on(userId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        authUserOrganizationService.assignedOrg(userId, orgIds, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/10 01:08
     */
    @Log(value = "插入用户", businessType = BusinessType.INSERT)
    @ApiOperation("插入用户")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(AuthUserEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getUsername(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.USER_USERNAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        // 初始化entity信息
        String salt = String.valueOf(System.currentTimeMillis());
        String password = Md5Util.encrypt("123456", salt);
        entity.setSalt(salt);
        entity.setPassword(password);
        entity.setIsLocked(AccountConstant.UN_LOCKED);
        Long id = authUserService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/10 01:08
     */
    @Log(value = "分页查询用户", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询用户")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<AuthUserDto>>> listPage(PageParam pageParam,
                                                                      AuthUserDto dto) {
        PageBean<AuthUserDto> pageBean = authUserService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/10 01:08
     */
    @Log(value = "更新用户", businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(AuthUserEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getUsername(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.USER_USERNAME_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        Long count = authUserService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/10 01:08
     */
    @Log(value = "根据id删除用户", logLevel = LogLevel.WARN, businessType = BusinessType.DELETE)
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        authUserService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 更新用户锁定状态
     *
     * @param entity 更新信息
     * @return {@link JsonResult}
     * @author darren
     * @since 2021/01/14 00:17
     */
    @Log(value = "更新用户锁定状态", logLevel = LogLevel.WARN, businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户锁定状态")
    @GetMapping("/updateLock")
    public ResponseEntity<JsonResult> updateLock(AuthUserEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getIsLocked(), new NotNullValidatorCallback(AuthErrorCodeEnum.USER_IS_LOCKED_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        authUserService.updateLock(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色ids，逗号分隔
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/23 01:48
     */
    @Log(value = "分配用户角色", businessType = BusinessType.UPDATE)
    @ApiOperation("分配角色")
    @GetMapping("/assignedRole")
    public ResponseEntity<JsonResult> assignedRole(@RequestParam(value = "userId", required = false) Long userId,
                                                   @RequestParam(value = "roleIds", required = false) String roleIds) {
        ValidatorBuilder.build()
                .on(userId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        authUserRoleService.assignedRole(userId, roleIds, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
