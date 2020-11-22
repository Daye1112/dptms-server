package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.security.SecurityUserDetails;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;
import com.darren1112.dptms.common.spi.common.entity.ActiveUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限相关Controller
 *
 * @author luyuhao
 * @date 2020/07/20 00:06
 */
@Slf4j
@Api(tags = "认证管理", description = "认证管理接口")
@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private SecurityUserDetails securityUserDetails;

    /**
     * 登录系统
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser}
     * @author luyuhao
     * @date 20/11/22 21:55
     */
    @ApiOperation("登录系统")
    @PostMapping("/login")
    public ResponseEntity<JsonResult<ActiveUser>> login(LoginParam loginParam,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) {
        ActiveUser activeUser = securityUserDetails.loginHandler(loginParam, request, response);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(activeUser));
    }
    //
    // /**
    //  * 认证接口-测试使用
    //  *
    //  * @param username   用户名
    //  * @param password   密码
    //  * @param grant_type 访问类型
    //  * @return null
    //  * @author luyuhao
    //  * @date 20/08/09 03:28
    //  */
    // @ApiOperation(value = "认证接口-测试使用", notes = "将test改为token，进行认证授权，获取token，header：Basic ZHB0bXM6MTIzNDU2")
    // @PostMapping("/oauth/test")
    // public ResponseEntity<JsonResult> testOauth(String username, String password, String grant_type) {
    //     return ResponseEntityUtil.ok(JsonResult.buildSuccessData(null));
    // }
    //
    // /**
    //  * 获取当前用户信息
    //  *
    //  * @param principal security用户对象
    //  * @return Principal
    //  * @author luyuhao
    //  * @date 20/08/09 03:29
    //  */
    // @ApiOperation(value = "获取当前用户信息")
    // @GetMapping("/user")
    // public Principal currentUser(Principal principal) {
    //     return principal;
    // }
    //
    // /**
    //  * 注销
    //  *
    //  * @param request 请求域
    //  * @return JsonResult
    //  * @author luyuhao
    //  * @date 20/08/09 03:34
    //  */
    // @ApiOperation(value = "注销")
    // @GetMapping("signOut")
    // public ResponseEntity<JsonResult> signOut(HttpServletRequest request) {
    //     String authorization = request.getHeader("Authorization");
    //     String token = StringUtil.replace(authorization, "bearer ", "");
    //     if (!consumerTokenServices.revokeToken(token)) {
    //         throw new BadRequestException("退出登录失败");
    //     }
    //     return ResponseEntityUtil.ok(JsonResult.buildMsg("退出登录成功"));
    // }
}
