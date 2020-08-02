package com.darren1112.dptms.common.component.oauth2;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理未登录响应-401
 *
 * @author luyuhao
 * @since 2019/12/10 9:43
 */
@Slf4j
@Component
public class DptmsAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401响应
        JsonResult jsonResult = JsonResult.buildErrorEnum(CommonErrorCodeEnum.INVALID_TOKEN);
        ResponseUtil.setJsonResult(response, jsonResult);
    }
}
