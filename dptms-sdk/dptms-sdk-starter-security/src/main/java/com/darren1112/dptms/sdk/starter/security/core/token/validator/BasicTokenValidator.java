package com.darren1112.dptms.sdk.starter.security.core.token.validator;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.constants.TokenValidatorConstant;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.base.TokenValidator;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 系统token校验器
 *
 * @author luyuhao
 * @since 2021/01/16 19:01
 */
public class BasicTokenValidator implements TokenValidator {

    private TokenStore tokenStore;

    public BasicTokenValidator(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * token校验处理
     *
     * @param request  请求域
     * @param response 响应域
     * @return true/false
     * @author luyuhao
     * @since 2020/11/28 10:47
     */
    @Override
    public boolean doValidate(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = tokenStore.getAccessToken(request);
        String refreshToken = tokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(accessToken) || StringUtil.isBlank(refreshToken)) {
            return false;
        }
        boolean flag;
        // token存在，校验是否合法
        String redisRefreshToken = tokenStore.getRefreshToken(accessToken);
        BaseSecurityUser activeUser = tokenStore.getActiveUser(refreshToken);
        if (StringUtil.isNotBlank(redisRefreshToken)
                && redisRefreshToken.equals(refreshToken)
                && Objects.nonNull(activeUser)) {
            // accessToken和refreshToken均有效 => 放行
            flag = true;
        } else if (StringUtil.isBlank(redisRefreshToken) && Objects.nonNull(activeUser)) {
            // accessToken无效, refreshToken有效 => 刷新accessToken，重置cookie
            tokenStore.refreshAccessTokenAndCookie(refreshToken, response);
            flag = true;
        } else {
            // accessToken和refreshToken均无效 => 打回
            // accessToken有效, refreshToken无效 => 打回
            tokenStore.removeTokenAndCookie(request, response);
            flag = false;
        }
        if (flag) {
            request.setAttribute(SecurityConstant.ACCESS_TOKEN_KEY, accessToken);
            request.setAttribute(SecurityConstant.REFRESH_TOKEN_KEY, refreshToken);
        }
        return flag;
    }

    /**
     * 校验失败时的错误码
     *
     * @return {@link SecurityErrorEnum}
     * @author luyuhao
     * @since 2022/11/17
     */
    @Override
    public SecurityErrorEnum validateError() {
        return SecurityErrorEnum.NOT_LOGIN;
    }

    /**
     * 校验器执行顺序
     *
     * @return {@link Integer}
     * @author luyuhao
     * @since 2022/11/17
     */
    @Override
    public Integer getOrder() {
        return TokenValidatorConstant.BASIC_TOKEN_VALIDATOR_ORDER;
    }
}
