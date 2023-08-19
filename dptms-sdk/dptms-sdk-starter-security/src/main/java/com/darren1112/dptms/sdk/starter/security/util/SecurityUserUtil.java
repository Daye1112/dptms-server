package com.darren1112.dptms.sdk.starter.security.util;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.ServiceHandleException;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.function.Function;

/**
 * 用户信息工具类
 *
 * @author darren
 * @since 2022/11/15
 */
public class SecurityUserUtil {

    /**
     * 获取当前用户
     *
     * @return {@link ActiveUser}
     * @author darren
     * @since 2022/11/15
     */
    public static ActiveUser getDefaultActiveUser() {
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication).orElse(null);
        Object principal = Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .orElse(null);
        return principal instanceof ActiveUser ? (ActiveUser) principal : new ActiveUser();
    }

    /**
     * 获取当前用户
     *
     * @return {@link ActiveUser}
     * @author darren
     * @since 2022/11/15
     */
    public static ActiveUser getActiveUser() {
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication).orElse(null);
        Object principal = Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .orElse(null);
        if (!(principal instanceof ActiveUser)) {
            throw new ServiceHandleException(CommonErrorCodeEnum.ACTIVE_USER_NULL);
        }
        return (ActiveUser) principal;
    }

    /**
     * 【默认值】获取当前用户id
     *
     * @return {@link Long}
     * @author darren
     * @since 2022/11/15
     */
    public static Long getUserId() {
        return Optional.ofNullable(getField(BaseSecurityUser::getUserId)).orElse(0L);
    }

    /**
     * 获取当前用户指定字段数据
     *
     * @param getFieldFun get方法
     * @return {@link T}
     * @author darren
     * @since 2022/11/15
     */
    public static <T> T getField(Function<ActiveUser, T> getFieldFun) {
        return Optional.ofNullable(getActiveUser()).map(getFieldFun).orElse(null);
    }
}
