package com.darren1112.dptms.common.security.starter.filter;

import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import org.apache.catalina.util.ParameterMap;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            setAttribute(activeUser, request);
            chain.doFilter(request, response);
        } finally {
            DptmsSecurityUtil.remove();
        }
    }

    /**
     * 添加用户信息到request的attribute中
     *
     * @param activeUser 用户信息
     * @param request    请求域
     * @author luyuhao
     * @since 2021/01/17 01:34
     */
    private void setAttribute(ActiveUser activeUser, HttpServletRequest request) {
        if (null != activeUser) {
            ParameterMap<String, String[]> parameterMap = (ParameterMap<String, String[]>) request.getParameterMap();
            parameterMap.setLocked(false);
            request.setAttribute("creater", activeUser.getId());
            request.setAttribute("updater", activeUser.getId());
            parameterMap.setLocked(true);
        }
    }
}
