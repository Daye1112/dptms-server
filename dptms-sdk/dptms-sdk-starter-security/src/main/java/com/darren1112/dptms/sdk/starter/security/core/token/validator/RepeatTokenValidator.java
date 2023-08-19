package com.darren1112.dptms.sdk.starter.security.core.token.validator;

import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.security.constants.TokenValidatorConstant;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.base.TokenValidator;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统token校验器
 *
 * @author darren
 * @since 2021/01/16 19:01
 */
public class RepeatTokenValidator implements TokenValidator {

    private TokenStore tokenStore;

    public RepeatTokenValidator(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * 重复登录校验
     *
     * @param request  请求域
     * @param response 响应域
     * @return true/false
     * @author darren
     * @since 2020/11/28 10:47
     */
    @Override
    public boolean doValidate(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = tokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(refreshToken)) {
            return false;
        }
        ActiveUser activeUser = tokenStore.getActiveUser(refreshToken);
        String userRefreshToken = tokenStore.getUserRefreshToken(activeUser);
        if (StringUtil.isBlank(userRefreshToken) || !userRefreshToken.equals(refreshToken)) {
            // 当前用户被挤下线，删除当前的token
            tokenStore.removeTokenAndCookie(request, response);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验失败时的错误码
     *
     * @return {@link SecurityErrorEnum}
     * @author darren
     * @since 2022/11/17
     */
    @Override
    public SecurityErrorEnum validateError() {
        return SecurityErrorEnum.REPEAT_LOGIN;
    }

    /**
     * 校验器执行顺序
     *
     * @return {@link Integer}
     * @author darren
     * @since 2022/11/17
     */
    @Override
    public Integer getOrder() {
        return TokenValidatorConstant.REPEAT_TOKEN_VALIDATOR_ORDER;
    }
}
