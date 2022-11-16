package com.darren1112.dptms.auth.common.security.processing.factory;

import com.darren1112.dptms.auth.common.security.enums.AuthTypeEnums;
import com.darren1112.dptms.auth.common.security.processing.token.PwdAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.base.processing.factory.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.base.processing.token.BaseAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.exceptions.AuthTokenErrorException;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 认证类型工厂
 *
 * @author luyuhao
 * @since 2022/6/6
 */
@Component
public class DptmsAuthTypeFactory implements AuthTypeFactory<ActiveUser> {

    /**
     * 创建参数token
     *
     * @param authType 认证类型
     * @return {@link BaseAuthenticationToken}
     * @author luyuhao
     * @since 2022/6/6
     */
    @Override
    public BaseAuthenticationToken createParamToken(Integer authType) {
        AuthTypeEnums authTypeEnums = AuthTypeEnums.matchByAuthType(authType);
        if (authTypeEnums == null) {
            throw new ProviderNotFoundException("认证类型错误!");
        }
        BaseAuthenticationToken token = null;
        switch (authTypeEnums) {
            case USERNAME_AND_PASSWORD:
                token = new PwdAuthenticationToken();
                break;
            default:
                throw new ProviderNotFoundException("认证类型错误!");
        }
        return token;
    }


    /**
     * 创建认证token
     *
     * @param user 用户信息
     * @return {@link BaseAuthenticationToken}
     * @author luyuhao
     * @since 2022/6/6
     */
    @Override
    public BaseAuthenticationToken createAuthToken(ActiveUser user) {
        if (user == null) {
            throw new AuthTokenErrorException("用户信息为空!");
        }
        AuthTypeEnums authTypeEnums = AuthTypeEnums.matchByAuthType(user.getAuthType());
        if (authTypeEnums == null) {
            throw new AuthTokenErrorException("认证类型错误!");
        }
        BaseAuthenticationToken token = null;
        switch (authTypeEnums) {
            case USERNAME_AND_PASSWORD:
                token = new PwdAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                break;
            default:
                throw new ProviderNotFoundException("认证类型错误!");
        }
        return token;
    }


}
