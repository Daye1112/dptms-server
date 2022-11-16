package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.DateUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.gateway.common.enums.GatewayErrorCodeEnum;
import com.darren1112.dptms.sdk.component.remoting.AuthRemoting;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限过滤器
 *
 * @author luyuhao
 * @since 2020/11/29 02:16
 */
@Slf4j
public class PermissionValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private AuthRemoting authRemoting;

    private TokenStore tokenStore;

    public PermissionValidFilter(SecurityProperties securityProperties, AuthRemoting authRemoting, TokenStore tokenStore) {
        this.securityProperties = securityProperties;
        this.authRemoting = authRemoting;
        this.tokenStore = tokenStore;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    private boolean shouldNotFilter(String uri) {
        //白名单校验
        return UrlUtil.matchUri(uri, securityProperties.getAnonUris())
                || UrlUtil.matchUri(uri, securityProperties.getAnonPermissionUris())
                || UrlUtil.matchUri(uri, FileConstant.STATIC_PATTERNS);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (shouldNotFilter(uri)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            // 获取redis中的用户信息
            ActiveUser activeUser = SecurityUserUtil.getActiveUser();
            // 查询权限list
            AuthUserDto userInfo = authRemoting.getNewInfo().getData();
            // 账号被删除
            if (userInfo == null) {
                ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.USER_ALREADY_DELETE));
                return;
            }
            // 账号被锁定
            if (userInfo.getIsLocked().equals(1)) {
                ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.USER_IS_LOCKED));
                return;
            }
            // 密码修改
            if (DateUtil.compare(userInfo.getPwdUpdateTime(), activeUser.getPwdUpdateTime()) != 0) {
                ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.PASSWORD_UPDATE));
                return;
            }
            // 判断uri是否在权限list中
            if (!checkPermission(userInfo.getPermissionList(), uri)) {
                log.info("用户: {} 访问无权限接口 {}", activeUser.getUsername(), uri);
                ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.FORBIDDEN));
                return;
            }
            // 重新设置用户信息
            ActiveUser.convert(activeUser, userInfo);
            tokenStore.updateActiveUser(activeUser);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(SecurityEnum.PERMISSION_VALID_ERROR));
        }
    }

    /**
     * 接口访问权限验证
     *
     * @param permissionList 权限list
     * @param uri            访问uri
     * @return true/false
     * @author luyuhao
     * @since 2021/01/17 23:25
     */
    private boolean checkPermission(List<AuthPermissionDto> permissionList, String uri) {
        if (CollectionUtil.isEmpty(permissionList)) {
            return false;
        }
        List<String> permissionUrlList = permissionList.stream().map(AuthPermissionDto::getPerUrl).collect(Collectors.toList());
        return UrlUtil.matchUri(uri, permissionUrlList);
    }
}
