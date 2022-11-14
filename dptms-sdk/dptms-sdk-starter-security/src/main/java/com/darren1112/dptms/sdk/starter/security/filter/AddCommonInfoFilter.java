package com.darren1112.dptms.sdk.starter.security.filter;

import com.darren1112.dptms.sdk.starter.security.core.DptmsTokenStore;
import com.darren1112.dptms.sdk.starter.security.core.ParameterRequestWrapper;
import com.darren1112.dptms.sdk.starter.security.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加用户信息
 *
 * @author luyuhao
 * @since 2021/01/17 01:25
 */
public class AddCommonInfoFilter extends OncePerRequestFilter {

    private DptmsTokenStore dptmsTokenStore;

    public AddCommonInfoFilter(DptmsTokenStore dptmsTokenStore) {
        this.dptmsTokenStore = dptmsTokenStore;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            // 获取用户信息
            ActiveUser activeUser = dptmsTokenStore.getActiveUser(request);
            // 设置用户信息
            DptmsSecurityUtil.set(activeUser);
            // 设置用户信息到请求域
            Map<String, Object> extParams = getExtParams(activeUser);
            ParameterRequestWrapper newRequestWrapper = new ParameterRequestWrapper(request, extParams);
            chain.doFilter(newRequestWrapper, response);
        } finally {
            DptmsSecurityUtil.remove();
        }
    }

    /**
     * 获取额外参数
     *
     * @param activeUser 用户信息
     * @return {@link Map}
     * @author luyuhao
     * @since 2021/01/17 01:34
     */
    private Map<String, Object> getExtParams(ActiveUser activeUser) {
        if (null == activeUser) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("creater", activeUser.getId());
        params.put("updater", activeUser.getId());
        return params;
    }
}
