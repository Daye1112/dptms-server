package com.darren1112.dptms.sdk.starter.security.core.security.factory;

import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.security.token.PwdAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.enums.AuthTypeEnum;
import com.darren1112.dptms.sdk.starter.security.exceptions.AuthTokenErrorException;
import org.springframework.security.authentication.ProviderNotFoundException;

/**
 * 认证类型工厂
 *
 * @author luyuhao
 * @since 2022/6/6
 */
public class BasicAuthTypeFactory implements AuthTypeFactory {

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
        AuthTypeEnum authTypeEnum = AuthTypeEnum.matchByAuthType(authType);
        if (authTypeEnum == null) {
            throw new ProviderNotFoundException("认证类型错误!");
        }
        BaseAuthenticationToken token = null;
        switch (authTypeEnum) {
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
    public <T extends BaseSecurityUser> BaseAuthenticationToken createAuthToken(T user) {
        if (user == null) {
            throw new AuthTokenErrorException("用户信息为空!");
        }
        AuthTypeEnum authTypeEnum = AuthTypeEnum.matchByAuthType(user.getAuthType());
        if (authTypeEnum == null) {
            throw new ProviderNotFoundException("认证类型错误!");
        }
        BaseAuthenticationToken token = null;
        switch (authTypeEnum) {
            case USERNAME_AND_PASSWORD:
                token = new PwdAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                break;
            default:
                throw new ProviderNotFoundException("认证类型错误!");
        }
        return token;
    }


}
