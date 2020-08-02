package com.darren1112.dptms.common.component.oauth2;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理无权限响应-403
 *
 * @author luyuhao
 * @date 2020/08/02 02:54
 */
public class DptmsAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JsonResult jsonResult = JsonResult.buildErrorEnum(CommonErrorCodeEnum.HAS_NO_PERMISSION);
        ResponseUtil.setJsonResult(response, jsonResult);
    }
}
