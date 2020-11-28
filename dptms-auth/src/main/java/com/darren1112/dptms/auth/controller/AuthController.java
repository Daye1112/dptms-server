package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.common.properties.AuthProperties;
import com.darren1112.dptms.auth.common.security.PasswordLoginHandler;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.redis.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;
import com.darren1112.dptms.common.spi.common.entity.ActiveUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限相关Controller
 *
 * @author luyuhao
 * @date 2020/07/20 00:06
 */
@Slf4j
@Api(tags = "认证管理", description = "认证管理接口")
@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private PasswordLoginHandler passwordLoginHandler;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AuthProperties authProperties;

    /**
     * 刷新accessToken
     *
     * @param refreshToken 熟悉token
     * @return refreshToken
     * @author luyuhao
     * @date 2020/11/28 11:16
     */
    @ApiOperation("刷新accessToken")
    @PostMapping("/refreshAccessToken")
    public ResponseEntity<JsonResult<String>> refreshAccessToken(String refreshToken) {
        ValidatorBuilder.build()
                .on(refreshToken, new NotEmptyValidatorCallback(AuthErrorCodeEnum.REFRESH_TOKEN_NOT_NULL))
                .doValidate().checkResult();
        String accessToken = tokenUtil.refreshAccessToken(refreshToken, authProperties.getAccessTokenExpired());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(accessToken));
    }

    /**
     * 登录系统
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser}
     * @author luyuhao
     * @date 20/11/22 21:55
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
}
