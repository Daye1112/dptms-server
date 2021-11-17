package com.darren1112.dptms.common.core.captcha.context;

import com.darren1112.dptms.common.core.util.UuidUtil;

/**
 * 验证码生成结果
 *
 * @author luyuhao
 * @since 2021/11/16
 */
public class CaptchaResult {

    /**
     * 验证码code
     */
    private String code;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码base64格式
     */
    private String base64;

    public CaptchaResult() {
        this.uuid = UuidUtil.generateDefault();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
