package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.service.SysUserService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 权限相关Controller
 *
 * @author luyuhao
 * @date 2020/07/20 00:06
 */
@Slf4j
@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signOut")
    public ResponseEntity signOut(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = StringUtil.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new BadRequestException("退出登录失败");
        }
        return ResponseEntityUtil.ok(JsonResult.buildMsg("退出登录成功"));
    }
}
