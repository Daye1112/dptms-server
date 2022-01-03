package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.AuthRoleMenuService;
import com.darren1112.dptms.auth.service.AuthRoleService;
import com.darren1112.dptms.auth.service.AuthUserRoleService;
import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 *
 * @author luyuhao
 * @since 2020/12/13 23:11
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthRoleMenuService authRoleMenuService;

    @Autowired
    private AuthUserRoleService authUserRoleService;

    /**
     * 分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单ids，逗号分隔
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/13 22:10
     */
    @Log(value = "分配角色菜单", businessType = BusinessType.UPDATE)
    @ApiOperation("分配菜单")
    @GetMapping("/assignedMenu")
    public ResponseEntity<JsonResult> assignedMenu(@RequestParam(value = "roleId", required = false) Long roleId,
                                                   @RequestParam(value = "menuIds", required = false) String menuIds) {
        ValidatorBuilder.build()
                .on(roleId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        authRoleMenuService.assignedMenu(roleId, menuIds, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/19 0:55
     */
    @Log(value = "插入角色", businessType = BusinessType.INSERT)
    @ApiOperation("插入角色")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(AuthRoleEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getRoleName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ROLE_NAME_NOT_NULL))
                .on(entity.getRoleCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ROLE_CODE_NOT_NULL))
                .on(entity.getIsAdmin(), new NotNullValidatorCallback(AuthErrorCodeEnum.ROLE_IS_ADMIN_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = authRoleService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 分页查询角色
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/19 1:44
     */
    @Log(value = "分页查询角色", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<AuthRoleDto>>> listPage(PageParam pageParam,
                                                                      AuthRoleDto dto) {
        PageBean<AuthRoleDto> pageBean = authRoleService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 更新角色信息
     *
     * @param entity 角色参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/19 1:51
     */
    @Log(value = "更新角色", businessType = BusinessType.UPDATE)
    @ApiOperation("更新角色")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(AuthRoleEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getRoleName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ROLE_NAME_NOT_NULL))
                .on(entity.getRoleCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ROLE_CODE_NOT_NULL))
                .on(entity.getIsAdmin(), new NotNullValidatorCallback(AuthErrorCodeEnum.ROLE_IS_ADMIN_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setUpdater(activeUser.getId());
        Long count = authRoleService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/19 1:56
     */
    @Log(value = "删除角色", businessType = BusinessType.DELETE)
    @ApiOperation("删除角色")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        authRoleService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 查询用户关联的角色list
     *
     * @param userId 用户id
     * @return {@link AuthRoleDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Log(value = "查询用户关联的角色list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询用户关联的角色list")
    @GetMapping("/listUserAssigned")
    public ResponseEntity<JsonResult<List<AuthRoleDto>>> listUserAssigned(@RequestParam(value = "userId", required = false) Long userId) {
        ValidatorBuilder.build()
                .on(userId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        List<AuthRoleDto> resultList = authUserRoleService.listUserAssigned(userId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }
}
