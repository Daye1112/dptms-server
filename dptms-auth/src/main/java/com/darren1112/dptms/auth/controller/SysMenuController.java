package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.SysMenuPermissionService;
import com.darren1112.dptms.auth.service.SysMenuService;
import com.darren1112.dptms.auth.service.SysRoleMenuService;
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
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单Controller
 *
 * @author luyuhao
 * @since 2020/12/12 17:28
 */
@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping(value = "/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysMenuPermissionService sysMenuPermissionService;

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/16 14:29
     */
    @Log(value = "插入菜单", businessType = BusinessType.INSERT)
    @ApiOperation("插入菜单")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(AuthMenuEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getMenuCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.MENU_CODE_NOT_NULL))
                .on(entity.getMenuName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.MENU_NAME_NOT_NULL))
                .on(entity.getMenuType(), new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_TYPE_NOT_NULL))
                .on(entity.getMenuParentId(), new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_PARENT_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = sysMenuService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/16 14:47
     */
    @Log(value = "根据id删除记录", businessType = BusinessType.DELETE)
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        sysMenuService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 查询菜单树
     *
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/19 0:08
     */
    @Log(value = "查询菜单树", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询菜单树")
    @GetMapping("/listTree")
    public ResponseEntity<JsonResult<AuthMenuDto>> listTree() {
        AuthMenuDto result = sysMenuService.listTree();
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/16 15:25
     */
    @Log(value = "更新菜单", businessType = BusinessType.UPDATE)
    @ApiOperation("更新菜单")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(AuthMenuEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getMenuCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.MENU_CODE_NOT_NULL))
                .on(entity.getMenuName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.MENU_NAME_NOT_NULL))
                .on(entity.getMenuType(), new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_TYPE_NOT_NULL))
                .on(entity.getMenuParentId(), new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_PARENT_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setUpdater(activeUser.getId());
        Long count = sysMenuService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 查询角色关联的菜单树
     *
     * @param roleId 角色id
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Log(value = "查询角色关联的菜单树", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询角色关联的菜单树")
    @GetMapping("/listRoleAssigned")
    public ResponseEntity<JsonResult<AuthMenuDto>> listRoleAssigned(@RequestParam(value = "roleId", required = false) Long roleId) {
        ValidatorBuilder.build()
                .on(roleId, new NotNullValidatorCallback(AuthErrorCodeEnum.ROLE_ID_NOT_NULL))
                .doValidate().checkResult();
        AuthMenuDto result = sysRoleMenuService.listRoleAssigned(roleId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 分配权限
     *
     * @param menuId 菜单id
     * @param perIds 权限ids，逗号分隔
     * @return {@link JsonResult)
     * @author baojiazhong
     * @since 2020/12/22 23:50
     */
    @Log(value = "分配权限", businessType = BusinessType.UPDATE)
    @ApiOperation("分配权限")
    @PostMapping("/assignedPer")
    public ResponseEntity<JsonResult> assignedPer(@RequestParam(value = "menuId", required = false) Long menuId,
                                                  @RequestParam(value = "perIds", required = false) String perIds) {
        ValidatorBuilder.build()
                .on(menuId, new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        sysMenuPermissionService.assignedPer(menuId, perIds, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

}
