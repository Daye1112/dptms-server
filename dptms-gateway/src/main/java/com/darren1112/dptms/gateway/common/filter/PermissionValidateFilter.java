package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.CookieUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.gateway.common.enums.GatewayErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 权限过滤器
 *
 * @author luyuhao
 * @date 2020/11/29 02:16
 */
@Slf4j
public class PermissionValidateFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private TokenUtil tokenUtil;

    public PermissionValidateFilter(SecurityProperties securityProperties, TokenUtil tokenUtil) {
        this.securityProperties = securityProperties;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 14:45
     */
    private boolean shouldNotFilter(String uri) {
        //白名单校验
        return UrlUtil.matchUri(uri, securityProperties.getAnonUris())
                || UrlUtil.matchUri(uri, FileConstant.STATIC_PATTERNS);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (shouldNotFilter(uri)) {
            chain.doFilter(request, response);
            return;
        }
        // token校验
        // 优先处理cookie中携带的cookie
        String refreshToken = CookieUtil.getCookie(SecurityConstant.REFRESH_TOKEN_KEY, request);
        if (StringUtil.isBlank(refreshToken)) {
            refreshToken = request.getHeader(SecurityConstant.REFRESH_TOKEN_KEY);
        }
        // 获取redis中的用户信息
        ActiveUser activeUser = tokenUtil.getActiveUser(refreshToken);
        if (Objects.isNull(activeUser)) {
            ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.NOT_LOGIN));
            return;
        }
        // TODO 【用户权限功能完成后开发】查询权限list

        // 判断uri是否在权限list中
        chain.doFilter(request, response);
    }
}
