package com.darren1112.dptms.common.core.captcha.context;

import com.darren1112.dptms.common.core.util.UuidUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 验证码生成结果
 *
 * @author darren
 * @since 2021/11/16
 */
public class CaptchaResult {

    /**
     * 验证码code
     */
    @JsonIgnore
    private String code;

    /**
     * captchaKey
     */
    private String captchaKey;

    /**
     * 验证码base64格式
     */
    private String base64;

    public CaptchaResult() {
        this.captchaKey = UuidUtil.generateDefault();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
