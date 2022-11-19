package com.darren1112.dptms.sdk.starter.security.core.security.factory.base;

import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.core.security.token.base.BaseAuthenticationToken;

/**
 * 认证类型工厂-基础类
 *
 * @author luyuhao
 * @since 2022/11/15
 */
public interface AuthTypeFactory {

    /**
     * 创建参数token
     *
     * @param authType 认证类型
     * @return {@link BaseAuthenticationToken}
     * @author luyuhao
     * @since 2022/6/6
     */
    BaseAuthenticationToken createParamToken(Integer authType);


    /**
     * 创建认证token
     *
     * @param user 用户信息
     * @return {@link BaseAuthenticationToken}
     * @author luyuhao
     * @since 2022/6/6
     */
    <T extends BaseSecurityUser> BaseAuthenticationToken createAuthToken(T user);
}