package com.darren1112.dptms.sdk.starter.security.base.processing.token;

import com.darren1112.dptms.sdk.starter.security.base.processing.base.AuthType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 基础token
 *
 * @author luyuhao
 * @since 2022/06/05
 */
public abstract class BaseAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public BaseAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    /**
     * 构建参数token
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link BaseAuthenticationToken}
     * @throws AuthenticationException 认证异常
     * @author luyuhao
     * @since 2022/6/6
     */
    public abstract BaseAuthenticationToken buildParamToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;

    /**
     * token对应的认证类型
     *
     * @return {@link AuthType}
     * @author luyuhao
     * @since 2022/6/6
     */
    public abstract AuthType authType();
}
