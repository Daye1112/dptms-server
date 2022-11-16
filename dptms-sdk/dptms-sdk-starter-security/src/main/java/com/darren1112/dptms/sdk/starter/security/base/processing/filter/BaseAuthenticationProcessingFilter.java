package com.darren1112.dptms.sdk.starter.security.base.processing.filter;

import com.darren1112.dptms.sdk.starter.security.base.processing.token.BaseAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础认证加工过滤器
 *
 * @author luyuhao
 * @since 2022/06/04
 */
public abstract class BaseAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 构造方法
     *
     * @author luyuhao
     * @since 2022/06/04
     */
    public BaseAuthenticationProcessingFilter(String loginPath, String httpMethod) {
        super(new AntPathRequestMatcher(loginPath, httpMethod));
    }

    /**
     * 构造方法
     *
     * @author luyuhao
     * @since 2022/06/04
     */
    public BaseAuthenticationProcessingFilter(String loginPath, String httpMethod,
                                              AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(loginPath, httpMethod));
        setAuthenticationManager(authenticationManager);
    }

    /**
     * 认证加工处理
     * 返回相应的{@link BaseAuthenticationToken}
     * 之后会交由provider进行认证处理
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link Authentication}
     * @throws AuthenticationException 认证异常
     * @throws IOException             io异常
     * @throws ServletException        servlet异常
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication token = null;
        try {
            return handle(request, response);
        } finally {
            finallyHandle(token, request, response);
        }
    }

    /**
     * 认证加工处理
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link Authentication}
     * @throws AuthenticationException 认证异常
     * @author luyuhao
     * @since 2022/06/04
     */
    protected abstract Authentication handle(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;

    /**
     * 后置加工处理
     * finally中调用
     *
     * @param token    token
     * @param request  请求域
     * @param response 响应域
     * @author luyuhao
     * @since 2022/06/04
     */
    protected void finallyHandle(Authentication token, HttpServletRequest request, HttpServletResponse response) {
        // do nothing
    }

    protected void setDetails(HttpServletRequest request,
                              BaseAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
