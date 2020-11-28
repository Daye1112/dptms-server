package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.gateway.common.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限过滤器
 *
 * @author luyuhao
 * @date 2020/11/29 02:16
 */
@Slf4j
@Component
public class PermissionValidateFilter  extends OncePerRequestFilter {

    @Autowired
    private SecurityProperties securityProperties;

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
        // TODO 查询用户信息
        // 判断是否更新过密码
        // 判断是否锁定
        // 查询权限list
        // 判断uri是否在权限list中
        chain.doFilter(request, response);
    }
}
