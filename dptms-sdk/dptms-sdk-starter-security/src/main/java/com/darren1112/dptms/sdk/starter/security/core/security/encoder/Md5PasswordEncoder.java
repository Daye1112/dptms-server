package com.darren1112.dptms.sdk.starter.security.core.security.encoder;

import com.darren1112.dptms.common.core.util.Md5Util;
import com.darren1112.dptms.common.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * md5+动态盐密码加密器
 *
 * @author luyuhao
 * @since 2022/11/15
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    private static final Logger log = LoggerFactory.getLogger(Md5PasswordEncoder.class);

    /**
     * 加密
     *
     * @param rawPassword 盐+原密码
     * @return {@link String}
     * @author luyuhao
     * @since 2022/11/15
     */
    @Override
    public String encode(CharSequence rawPassword) {
        if (StringUtil.isEmpty(rawPassword)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        return Md5Util.encrypt(rawPassword.toString());
    }

    /**
     * 密码校验
     *
     * @param rawPassword     盐+用户提交密码
     * @param encodedPassword 加密后的密码
     * @return {@link boolean}
     * @author luyuhao
     * @since 2022/11/15
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StringUtil.isEmpty(rawPassword)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (StringUtil.isEmpty(encodedPassword)) {
            log.warn("原密码串为空");
        }
        String rawPasswordEncrypt = Md5Util.encrypt(rawPassword.toString());
        return rawPasswordEncrypt.equals(encodedPassword);
    }
}
