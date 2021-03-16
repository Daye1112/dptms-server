package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.SysMenuPermissionService;
import com.darren1112.dptms.auth.service.SysPermissionService;
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
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限Controller
 *
 * @author luyuhao
 * @since 2020/12/10 00:58
 */
@Slf4j
@Api(tags = "权限管理")
@RestController
@RequestMapping(value = "/permission")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysMenuPermissionService sysMenuPermissionService;

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "插入权限", businessType = BusinessType.INSERT)
    @ApiOperation("插入权限")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(SysPermissionEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getPerName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_NAME_NOT_NULL))
                .on(entity.getPerCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_CODE_NOT_NULL))
                .on(entity.getPerGroup(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_GROUP_NOT_NULL))
                .on(entity.getPerUrl(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_URL_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = sysPermissionService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 分页查询权限
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "分页查询权限", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询权限")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysPermissionDto>>> listPage(PageParam pageParam,
                                                                           SysPermissionDto dto) {
        PageBean<SysPermissionDto> pageBean = sysPermissionService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 更新权限信息
     *
     * @param entity 权限参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "更新权限", businessType = BusinessType.UPDATE)
    @ApiOperation("更新权限")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(SysPermissionEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getPerName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_NAME_NOT_NULL))
                .on(entity.getPerCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_CODE_NOT_NULL))
                .on(entity.getPerGroup(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_GROUP_NOT_NULL))
                .on(entity.getPerUrl(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PER_URL_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        entity.setUpdater(activeUser.getId());
        Long count = sysPermissionService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "根据id删除记录", businessType = BusinessType.DELETE)
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        sysPermissionService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 查询与菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto)
     * @author baojiazhong
     * @since 2020/12/22 23:09
     */
    @Log(value = "查询菜单绑定的权限list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询菜单绑定的权限list")
    @GetMapping("/listMenuAssigned")
    public ResponseEntity<JsonResult<List<SysPermissionDto>>> listMenuAssigned(@RequestParam(value = "menuId", required = false) Long menuId) {
        ValidatorBuilder.build()
                .on(menuId, new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_ID_NOT_NULL))
                .doValidate().checkResult();
        List<SysPermissionDto> list = sysMenuPermissionService.listMenuAssigned(menuId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }

    /**
     * 查询权限组list
     *
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @since 2020/12/28 01:10
     */
    @Log(value = "查询权限组list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询权限组list")
    @GetMapping("/listGroup")
    public ResponseEntity<JsonResult<List<SysPermissionDto>>> listGroup() {
        List<SysPermissionDto> list = sysPermissionService.listGroup();
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }

    /**
     * 根据菜单id查询权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto}
     * @author luyuhao
     * @since 2021/01/04 23:52
     */
    @Log(value = "根据菜单id查询权限list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("根据菜单id查询权限list")
    @GetMapping("/listByMenuId")
    public ResponseEntity<JsonResult<List<SysPermissionDto>>> listByMenuId(@RequestParam(value = "menuId", required = false) Long menuId) {
        ValidatorBuilder.build()
                .on(menuId, new NotNullValidatorCallback(AuthErrorCodeEnum.MENU_ID_NOT_NULL))
                .doValidate().checkResult();
        List<SysPermissionDto> list = sysMenuPermissionService.listByMenuId(menuId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }
}
