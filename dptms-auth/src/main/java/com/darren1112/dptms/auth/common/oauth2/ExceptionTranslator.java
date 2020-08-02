package com.darren1112.dptms.auth.common.oauth2;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 异常翻译config
 *
 * @author luyuhao
 * @date 2020/08/02 00:45
 */
@Slf4j
@Component
public class ExceptionTranslator implements WebResponseExceptionTranslator {

    public static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";

    public static final String LOCKED = "locked";

    /**
     * 异常翻译
     *
     * @param e 异常
     * @return 响应
     * @author luyuhao
     * @date 20/08/02 01:51
     */
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        if (e instanceof UnsupportedGrantTypeException) {
            //不支持该认证类型
            return ResponseEntityUtil.badRequest(JsonResult.buildErrorEnum(AuthErrorCodeEnum.UNSUPPORTED_GRANT_TYPE));
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtil.containsIgnoreCase(e.getMessage(), INVALID_REFRESH_TOKEN)) {
                //refreshToken无效
                return ResponseEntityUtil.badRequest(JsonResult.buildErrorEnum(AuthErrorCodeEnum.INVALID_REFRESH_TOKEN));
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), LOCKED)) {
                //用户已被锁定
                return ResponseEntityUtil.badRequest(JsonResult.buildErrorEnum(AuthErrorCodeEnum.LOCKED));
            }
            //用户名或密码错误
            return ResponseEntityUtil.badRequest(JsonResult.buildErrorEnum(AuthErrorCodeEnum.LOGIN_FAILURE));
        }
        //认证失败
        log.error("认证失败", e);
        return ResponseEntityUtil.unauthorized(JsonResult.buildErrorEnum(AuthErrorCodeEnum.AUTHORIZED_FAILURE));
    }
}
