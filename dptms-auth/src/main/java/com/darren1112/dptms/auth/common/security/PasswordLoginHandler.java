package com.darren1112.dptms.auth.common.security;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.common.security.base.BaseUserDetails;
import com.darren1112.dptms.auth.service.SysUserService;
import com.darren1112.dptms.common.core.constants.AccountConstant;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.exception.BaseException;
import com.darren1112.dptms.common.core.util.Md5Util;
import com.darren1112.dptms.common.security.starter.core.DptmsTokenStore;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 用户信息鉴权
 *
 * @author luyuhao
 * @date 2020/11/22 17:13
 */
@Component
public class PasswordLoginHandler extends BaseUserDetails {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DptmsTokenStore dptmsTokenStore;

    /**
     * 前置处理
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 17:26
     */
    @Override
    public void preHandler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException {
        // TODO 邮箱验证、验证码登录等
    }

    /**
     * 处理
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser}
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 21:07
     */
    @Override
    public ActiveUser handler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException {
        //查询用户信息
        SysUserDto sysUserDto = sysUserService.getByUsername(loginParam.getUsername());
        //用户不存在
        if (Objects.isNull(sysUserDto)) {
            throw new BadRequestException(AuthErrorCodeEnum.LOGIN_FAILURE);
        }
        //验证密码是否正确
        if (!Md5Util.match(loginParam.getPassword(), sysUserDto.getSalt(), sysUserDto.getPassword())) {
            throw new BadRequestException(AuthErrorCodeEnum.LOGIN_FAILURE);
        }
        if (sysUserDto.getIsLocked().equals(AccountConstant.IS_LOCKED)) {
            throw new BadRequestException(AuthErrorCodeEnum.LOCKED);
        }
        return convert(sysUserDto);
    }

    /**
     * 后置处理
     *
     * @param activeUser 用户信息
     * @param request    请求域
     * @param response   响应域
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 17:42
     */
    @Override
    public void afterHandler(ActiveUser activeUser, HttpServletRequest request, HttpServletResponse response) throws BaseException {
        // 后置处理
        // 更新登录时间
        sysUserService.updateLoginTime(activeUser.getId());
        // 生成token并保存到redis和cookie中
        dptmsTokenStore.generateToken(activeUser, response);
    }

    /**
     * 对象转换
     * 返回基本信息
     *
     * @param sysUserDto 用户信息
     * @return {@link ActiveUser}
     * @author luyuhao
     * @date 20/11/22 22:24
     */
    private ActiveUser convert(SysUserDto sysUserDto) {
        ActiveUser activeUser = new ActiveUser();
        activeUser.setId(sysUserDto.getId());
        activeUser.setUsername(sysUserDto.getUsername());
        activeUser.setRealName(sysUserDto.getRealName());
        activeUser.setGender(sysUserDto.getGender());
        activeUser.setPhoneNumber(sysUserDto.getPhoneNumber());
        activeUser.setEmail(sysUserDto.getEmail());
        activeUser.setLastLoginTime(sysUserDto.getLastLoginTime());
        activeUser.setFileId(sysUserDto.getFileId());
        return activeUser;
    }
}
