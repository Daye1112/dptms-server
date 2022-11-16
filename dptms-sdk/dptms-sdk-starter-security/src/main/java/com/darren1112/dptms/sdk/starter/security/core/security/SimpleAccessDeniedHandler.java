package com.darren1112.dptms.sdk.starter.security.core.security;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无访问权限处理器
 *
 * @author luyuhao
 * @since 2022/06/05
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);

    /**
     * 无权限处理逻辑
     *
     * @param request               请求域
     * @param response              响应域
     * @param accessDeniedException 访问限制异常
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("失败信息: 无权限, ip: {}, 请求接口: {}", IpUtil.getIp(request), request.getRequestURI());
        ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(SecurityEnum.HAS_NO_PERMISSION));
    }
}
