package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysMenuService;
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
     * @param id    记录id
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
     * 分页查询菜单
     *
     * @param pageParam 分页参数
     * @param dto       查询参数
     * @author baojiazhong
     * @return {@link JsonResult)
     * @date 2020/12/19 0:08
     */
    @ApiOperation("分页查询菜单")
    @PostMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysMenuDto>>> listPage(PageParam pageParam,
                                                                     SysMenuDto dto) {
        PageBean<SysMenuDto> pageBean = sysMenuService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
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


}
