package com.darren1112.dptms.sdk.starter.security.core.security.token;

import com.darren1112.dptms.sdk.starter.security.base.processing.base.AuthType;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.enums.AuthTypeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 账号密码认证token
 *
 * @author luyuhao
 * @since 2022/11/15
 */
public class PwdAuthenticationToken extends BaseAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 账号
     */
    private Object principal;

    /**
     * 密码
     */
    private Object credentials;

    /**
     * 验证码code
     */
    private String captchaCode;

    /**
     * 验证码key
     */
    private String captchaKey;

    public PwdAuthenticationToken() {
        super(null);
        setAuthenticated(false);
    }

    public PwdAuthenticationToken(Object principal,
                                  Object credentials,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    /**
     * 构建参数token
     *
     * @param request  请求域
     * @param response 响应域
     * @throws AuthenticationException 认证异常
     * @author luyuhao
     * @since 2022/6/6
     */
    @Override
    public BaseAuthenticationToken buildParamToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        this.principal = request.getParameter("username");
        this.credentials = request.getParameter("password");
        this.captchaCode = request.getParameter("captchaCode");
        this.captchaKey = request.getParameter("captchaKey");
        return this;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    /**
     * token对应的认证类型
     *
     * @return {@link AuthType}
     * @author luyuhao
     * @since 2022/6/6
     */
    @Override
    public AuthType authType() {
        return AuthTypeEnum.USERNAME_AND_PASSWORD;
    }
}
