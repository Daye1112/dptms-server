package com.darren1112.dptms.sdk.starter.security.filter;

import com.darren1112.dptms.common.core.web.ParameterRequestWrapper;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加用户信息过滤器
 *
 * @author darren
 * @since 2021/01/17 01:25
 */
@Slf4j
public class SecurityUserFilter extends OncePerRequestFilter {

    private TokenStore tokenStore;

    private AuthTypeFactory authTypeFactory;

    public SecurityUserFilter(TokenStore tokenStore, AuthTypeFactory authTypeFactory) {
        this.tokenStore = tokenStore;
        this.authTypeFactory = authTypeFactory;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取用户信息
        BaseSecurityUser activeUser = tokenStore.getActiveUser(request);
        if (activeUser == null) {
            chain.doFilter(request, response);
            return;
        }

        // 设置用户信息到请求域
        Map<String, Object> extParams = getExtParams(activeUser);
        ParameterRequestWrapper newRequestWrapper = new ParameterRequestWrapper(request, extParams);

        // 设置上下文
        BaseAuthenticationToken token = authTypeFactory.createAuthToken(activeUser);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(newRequestWrapper));
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(newRequestWrapper, response);
    }

    /**
     * 获取额外参数
     *
     * @param activeUser 用户信息
     * @return {@link Map}
     * @author darren
     * @since 2021/01/17 01:34
     */
    private Map<String, Object> getExtParams(BaseSecurityUser activeUser) {
        if (null == activeUser) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("creater", activeUser.getUserId());
        params.put("updater", activeUser.getUserId());
        return params;
    }
}
