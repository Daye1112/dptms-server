package com.darren1112.dptms.common.security.starter.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenValidator;
import com.darren1112.dptms.common.security.starter.enums.SecurityEnum;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
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
 * @date 2021/01/16 18:59
 */
@Slf4j
public class DptmsTokenValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private DptmsTokenValidator dptmsTokenValidator;

    public DptmsTokenValidFilter(SecurityProperties securityProperties, DptmsTokenValidator dptmsTokenValidator) {
        this.securityProperties = securityProperties;
        this.dptmsTokenValidator = dptmsTokenValidator;
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

    /**
     * 执行内容
     * 1. accessToken和refreshToken均无效 => 打回
     * 2. accessToken和refreshToken均有效 => 放行
     * 3. accessToken无效, refreshToken有效 => 刷新token，重置cookie
     * 4. accessToken有效, refreshToken无效 => 打回
     * 5. 适当延长refreshToken有效期
     *
     * @author luyuhao
     * @date 20/08/02 14:46
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
            if (!dptmsTokenValidator.tokenValidHandle(request, response)) {
                ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(SecurityEnum.NOT_LOGIN));
                return;
            }
            chain.doFilter(request, response);
            DptmsSecurityUtil.remove();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(SecurityEnum.TOKEN_VALID_ERROR));
        }
    }
}
