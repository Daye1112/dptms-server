package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.constants.FileConstant;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.DateUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.security.starter.enums.SecurityEnum;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.gateway.common.enums.GatewayErrorCodeEnum;
import com.darren1112.dptms.gateway.remoting.AuthRemoting;
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
 * @date 2020/11/29 02:16
 */
@Slf4j
public class PermissionValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private AuthRemoting authRemoting;

    private DptmsTokenStore dptmsTokenStore;

    public PermissionValidFilter(SecurityProperties securityProperties, AuthRemoting authRemoting, DptmsTokenStore dptmsTokenStore) {
        this.securityProperties = securityProperties;
        this.authRemoting = authRemoting;
        this.dptmsTokenStore = dptmsTokenStore;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 14:45
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
            ActiveUser activeUser = DptmsSecurityUtil.get();
            // 查询权限list
            SysUserDto userInfo = authRemoting.getNewInfo().getData();
            // 账号被删除
            if (userInfo == null) {
                ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.USER_ALREADY_DELETE));
                return;
            }
            // 账号被锁定
            if (userInfo.getIsLocked().equals(1)) {
                ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.USER_IS_LOCKED));
                return;
            }
            // 密码修改
            if (DateUtil.compare(userInfo.getPwdUpdateTime(), activeUser.getPwdUpdateTime()) != 0) {
                ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.PASSWORD_UPDATE));
                return;
            }
            // 判断uri是否在权限list中
            if (!checkPermission(userInfo.getPermissionList(), uri)) {
                log.info("用户: {} 访问无权限接口 {}", activeUser.getUsername(), uri);
                ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(GatewayErrorCodeEnum.FORBIDDEN));
                return;
            }
            // 重新设置用户信息
            ActiveUser.convert(activeUser, userInfo);
            dptmsTokenStore.updateActiveUser(activeUser);
            DptmsSecurityUtil.set(activeUser);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ResponseUtil.setJsonResult(response, JsonResult.buildErrorEnum(SecurityEnum.PERMISSION_VALID_ERROR));
        }
    }

    /**
     * 接口访问权限验证
     *
     * @param permissionList 权限list
     * @param uri            访问uri
     * @return true/false
     * @author luyuhao
     * @date 2021/01/17 23:25
     */
    private boolean checkPermission(List<SysPermissionDto> permissionList, String uri) {
        if (CollectionUtil.isEmpty(permissionList)) {
            return false;
        }
        List<String> permissionUrlList = permissionList.stream().map(SysPermissionDto::getPerUrl).collect(Collectors.toList());
        return UrlUtil.matchUri(uri, permissionUrlList);
    }
}
