package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.common.security.PasswordLoginHandler;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录管理Controller
 *
 * @author luyuhao
 * @since 2021/01/17 19:26
 */
@Slf4j
@Api(tags = "登录管理", description = "登录管理接口")
@RestController
@RequestMapping(value = "")
public class LoginController {

    @Autowired
    private PasswordLoginHandler passwordLoginHandler;

    @Autowired
    private DptmsTokenStore dptmsTokenStore;

    /**
     * 登录系统
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser}
     * @author luyuhao
     * @since 20/11/22 21:55
     */
    @ApiOperation("登录系统")
    @PostMapping("/login")
    public ResponseEntity<JsonResult<ActiveUser>> login(LoginParam loginParam,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) {
        ValidatorBuilder.build()
                .on(loginParam.getUsername(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.USERNAME_NOT_NULL))
                .on(loginParam.getPassword(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.PASSWORD_NOT_NULL))
                .on(loginParam.getLoginType(), new NotNullValidatorCallback(AuthErrorCodeEnum.LOGIN_TYPE_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = passwordLoginHandler.loginHandler(loginParam, request, response);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(activeUser));
    }

    /**
     * 登出系统
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link ActiveUser}
     * @author luyuhao
     * @since 20/11/22 21:55
     */
    @ApiOperation("登出系统")
    @GetMapping("/logout")
    public ResponseEntity<JsonResult<ActiveUser>> logout(HttpServletRequest request,
                                                         HttpServletResponse response) {
        dptmsTokenStore.removeTokenAndCookie(request, response);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

}
