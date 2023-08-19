package com.darren1112.dptms.auth.common.security.processing.provider;

import com.darren1112.dptms.auth.service.AuthUserService;
import com.darren1112.dptms.common.core.constants.AccountConstant;
import com.darren1112.dptms.common.core.constants.RedisConstant;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.security.base.processing.base.AuthType;
import com.darren1112.dptms.sdk.starter.security.base.processing.provider.BaseAuthenticationProvider;
import com.darren1112.dptms.sdk.starter.security.core.security.factory.base.AuthTypeFactory;
import com.darren1112.dptms.sdk.starter.security.core.security.token.PwdAuthenticationToken;
import com.darren1112.dptms.sdk.starter.security.enums.AuthTypeEnum;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import com.darren1112.dptms.sdk.starter.security.exceptions.CaptchaCodeErrorException;
import com.darren1112.dptms.sdk.starter.security.exceptions.CaptchaInvalidException;
import com.darren1112.dptms.sdk.starter.security.exceptions.UsernamePwdErrorException;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * 账号密码校验器
 *
 * @author darren
 * @since 2022/11/15
 */
@Slf4j
@Component
public class PwdAuthenticationProvider extends BaseAuthenticationProvider {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthTypeFactory authTypeFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PwdAuthenticationToken token = (PwdAuthenticationToken) authentication;
        captchaValid(token);

        String username = Optional.ofNullable(token.getPrincipal()).map(String::valueOf).orElse("");
        String password = Optional.ofNullable(token.getCredentials()).map(String::valueOf).orElse("");

        // 查询用户信息
        AuthUserDto sysUserDto = authUserService.getByUsername(username);
        // 用户不存在
        if (Objects.isNull(sysUserDto)) {
            throw new UsernamePwdErrorException();
        }
        // 密码校验
        boolean checkPassword = passwordEncoder.matches(sysUserDto.getSalt() + password, sysUserDto.getPassword());
        if (!checkPassword) {
            throw new UsernamePwdErrorException();
        }
        // 锁定校验
        if (sysUserDto.getIsLocked().equals(AccountConstant.IS_LOCKED)) {
            throw new LockedException(SecurityErrorEnum.LOCKED.getMessage());
        }
        ActiveUser activeUser = ActiveUser.convert(sysUserDto);
        activeUser.setAuthType(authType().getAuthType());
        return authTypeFactory.createAuthToken(activeUser);
    }

    /**
     * 验证码验证
     *
     * @param token token
     * @author darren
     * @since 2022/11/15
     */
    private void captchaValid(PwdAuthenticationToken token) {
        String captchaCode = Optional.ofNullable(token.getCaptchaCode()).orElse("");
        String captchaKey = Optional.ofNullable(token.getCaptchaKey()).orElse("");
        // 验证码验证
        String realCode = redisUtil.get(RedisConstant.CAPTCHA_PREFIX + captchaKey);
        // 移除验证码
        redisUtil.removeKey(RedisConstant.CAPTCHA_PREFIX + captchaKey);
        // 为空判断
        if (StringUtil.isBlank(realCode)) {
            throw new CaptchaInvalidException();
        }
        // 是否相同验证
        if (!realCode.equalsIgnoreCase(captchaCode)) {
            throw new CaptchaCodeErrorException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authType().getTokenClass().isAssignableFrom(authentication);
    }

    /**
     * token对应的认证类型
     *
     * @return {@link AuthType}
     * @author darren
     * @since 2022/6/6
     */
    @Override
    public AuthType authType() {
        return AuthTypeEnum.USERNAME_AND_PASSWORD;
    }
}
