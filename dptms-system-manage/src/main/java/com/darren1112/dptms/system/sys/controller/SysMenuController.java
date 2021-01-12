package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysMenuPermissionService;
import com.darren1112.dptms.system.sys.service.SysMenuService;
import com.darren1112.dptms.system.sys.service.SysRoleMenuService;
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
@RequestMapping(value = "/sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysMenuPermissionService sysMenuPermissionService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @date 2020/12/16 14:29
     */
    @ApiOperation("插入菜单")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(SysMenuEntity entity) {

        ValidatorBuilder.build()
                .on(entity.getMenuCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.MENU_CODE_NOT_NULL))
                .on(entity.getMenuName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.MENU_NAME_NOT_NULL))
                .on(entity.getMenuType(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.MENU_TYPE_NOT_NULL))
                .on(entity.getMenuParentId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.MENU_PARENT_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
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
     * @date 2020/12/16 14:47
     */
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        sysMenuService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 查询菜单树
     *
     * @return {@link JsonResult)
     * @author baojiazhong
     * @date 2020/12/19 0:08
     */
    @ApiOperation("查询菜单树")
    @GetMapping("/listTree")
    public ResponseEntity<JsonResult<SysMenuDto>> listTree() {
        SysMenuDto result = sysMenuService.listTree();
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link JsonResult)
     * @author baojiazhong
     * @date 2020/12/16 15:25
     */
    @ApiOperation("更新菜单")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(SysMenuEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getMenuCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.MENU_CODE_NOT_NULL))
                .on(entity.getMenuName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.MENU_NAME_NOT_NULL))
                .on(entity.getMenuType(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.MENU_TYPE_NOT_NULL))
                .on(entity.getMenuParentId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.MENU_PARENT_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        Long count = sysMenuService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 查询角色关联的菜单list
     *
     * @param roleId 角色id
     * @return {@link SysMenuDto}
     * @author luyuhao
     * @date 20/12/13 21:43
     */
    @ApiOperation("查询角色关联的菜单Tree")
    @GetMapping("/listRoleAssigned")
    public ResponseEntity<JsonResult<SysMenuDto>> listRoleAssigned(@RequestParam(value = "roleId", required = false) Long roleId) {
        ValidatorBuilder.build()
                .on(roleId, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ROLE_ID_NOT_NULL))
                .doValidate().checkResult();
        SysMenuDto result = sysRoleMenuService.listRoleAssigned(roleId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 分配权限
     *
     * @param menuId 菜单id
     * @param perIds 权限ids，逗号分隔
     * @return {@link JsonResult)
     * @author baojiazhong
     * @date 2020/12/22 23:50
     */
    @ApiOperation("分配权限")
    @PostMapping("/assignedPer")
    public ResponseEntity<JsonResult> assignedPer(@RequestParam(value = "menuId", required = false) Long menuId,
                                                  @RequestParam(value = "perIds", required = false) String perIds) {
        ValidatorBuilder.build()
                .on(menuId, new NotNullValidatorCallback(SystemManageErrorCodeEnum.MENU_ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        sysMenuPermissionService.assignedPer(menuId, perIds, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

}
