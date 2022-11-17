package com.darren1112.dptms.auth.common.security.processing.filter;

import com.darren1112.dptms.common.core.constants.FilterOrderConstant;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.security.base.processing.filter.BaseAuthenticationProcessingFilter;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证加工处理器-实现类
 *
 * @author luyuhao
 * @since 2022/11/15
 */
@Slf4j
@Order(FilterOrderConstant.LOGIN_PROCESSING_FILTER)
public class DptmsAuthenticationProcessingFilter extends BaseAuthenticationProcessingFilter {

    private AuthTypeFactory authTypeFactory;

    public DptmsAuthenticationProcessingFilter(SecurityProperties securityProperties,
                                               AuthenticationManager authenticationManager,
                                               AuthTypeFactory authTypeFactory) {
        super(securityProperties.getLoginPath(), "POST", authenticationManager);
        this.authTypeFactory = authTypeFactory;
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
    @Override
    protected Authentication handle(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("DptmsAuthenticationProcessingFilter, uri:{}", request.getRequestURI());
        // 获取认证类型
        String authTypeParam = request.getParameter("authType");
        if (StringUtil.isBlank(authTypeParam)) {
            throw new ProviderNotFoundException("认证类型错误!");
        }
        // 根据认证类型创建相应的token
        BaseAuthenticationToken token = authTypeFactory.createParamToken(Integer.valueOf(authTypeParam))
                .buildParamToken(request, response);
        setDetails(request, token);
        return this.getAuthenticationManager().authenticate(token);
    }
}
