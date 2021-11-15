package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.OnlineUserService;
import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在线用户管理
 *
 * @author luyuhao
 * @since 2021/01/31 21:50
 */
@Slf4j
@Api(tags = "在线用户管理", description = "在线用户管理接口")
@RestController
@RequestMapping(value = "/onlineUser")
public class OnlineUserController extends BaseController {

    @Autowired
    private OnlineUserService onlineUserService;

    /**
     * 分页查询在线用户
     *
     * @param activeUser 查询条件
     * @param pageParam  分页参数
     * @return {@link ActiveUser}
     * @author luyuhao
     * @since 2021/01/31 21:52
     */
    @Log(value = "分页查询在线用户", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询在线用户")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<ActiveUser>>> listPage(ActiveUser activeUser, PageParam pageParam) {
        PageBean<ActiveUser> resultBean = onlineUserService.listPage(activeUser, getPageParam(pageParam));
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultBean));
    }

    /**
     * 强制下线用户
     *
     * @param userId 用户id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/01/31 22:57
     */
    @Log(value = "强制下线用户", logLevel = LogLevel.WARN, businessType = BusinessType.FORCE)
    @ApiOperation("强制下线用户")
    @GetMapping("/forcedOffline")
    public ResponseEntity<JsonResult> forcedOffline(@RequestParam(value = "userId", required = false) Long userId) {
        ValidatorBuilder.build()
                .on(userId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        onlineUserService.forcedOffline(userId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
