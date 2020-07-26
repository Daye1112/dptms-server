package com.darren1112.dptms.auth.common.oauth2;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.common.message.JsonResult;
import com.darren1112.dptms.common.util.JsonUtil;
import com.darren1112.dptms.common.util.ResponseEntityUtil;
import com.darren1112.dptms.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * 自定义返回的错误信息
 *
 * @author luyuhao
 * @date 2020/07/26 21:21
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("系统捕捉AuthenticationException异常并处理 ==> " + exception.getMessage());
        String message = StringUtil.isContainChinese(exception.getMessage()) ? exception.getMessage() : null;
        JsonResult jsonResult;
        //错误类型
        if (StringUtil.isNotEmpty(message) && message.equals(AuthErrorCodeEnum.INSUFFICIENT_PERMISSION.getMessage())) {
            //权限不足
            jsonResult = JsonResult.buildErrorMsg(HttpStatus.FORBIDDEN.value(),
                    AuthErrorCodeEnum.INSUFFICIENT_PERMISSION.getMessage());
            response.setStatus(AuthErrorCodeEnum.INSUFFICIENT_PERMISSION.getCode());
        } else {
            //未登录
            jsonResult = JsonResult.buildErrorMsg(HttpStatus.UNAUTHORIZED.value(),
                    Optional.ofNullable(message).orElse(AuthErrorCodeEnum.AUTHORIZED_FAILURE.getMessage()));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        String jsonString = JsonUtil.toJsonString(jsonResult);
        response.setContentType(ResponseEntityUtil.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.close();
    }
}
