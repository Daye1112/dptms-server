package com.darren1112.dptms.sdk.starter.security.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.sdk.starter.security.core.token.manager.TokenValidatorManager;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.base.TokenValidator;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 【网关中使用】
 * token过滤器
 *
 * @author luyuhao
 * @since 2021/01/16 18:59
 */
@Slf4j
public class AccessTokenValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private TokenValidatorManager tokenValidatorManager;

    public AccessTokenValidFilter(SecurityProperties securityProperties, TokenValidatorManager tokenValidatorManager) {
        this.securityProperties = securityProperties;
        this.tokenValidatorManager = tokenValidatorManager;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    private boolean shouldNotFilter(String uri) {
        //白名单校验
        return UrlUtil.matchUri(uri, securityProperties.getAnonUris())
                || UrlUtil.matchUri(uri, FileConstant.STATIC_PATTERNS);
    }

    /**
     * 执行内容
     * 1. accessToken和refreshToken均无效 => 打回
     * 2. accessToken和refreshToken均有效 => 放行
     * 3. accessToken无效, refreshToken有效 => 刷新token，重置cookie
     * 4. accessToken有效, refreshToken无效 => 打回
     * 5. 适当延长refreshToken有效期
     *
     * @author luyuhao
     * @since 20/08/02 14:46
     */
    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (shouldNotFilter(uri)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            // token校验
            for (TokenValidator tokenValidator : tokenValidatorManager.getTokenValidatorList()) {
                if (!tokenValidator.doValidate(request, response)) {
                    ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(tokenValidator.validateError()));
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(SecurityErrorEnum.TOKEN_VALID_ERROR));
        }
    }
}
