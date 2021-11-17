package com.darren1112.dptms.common.core.captcha;

import com.darren1112.dptms.common.core.captcha.context.CaptchaParam;
import com.darren1112.dptms.common.core.captcha.context.CaptchaResult;
import com.wf.captcha.SpecCaptcha;

import java.awt.*;
import java.io.IOException;

/**
 * 验证码工具类
 *
 * @author luyuhao
 * @since 2021/11/16
 */
public class CaptchaUtil {

    /**
     * 生成图片格式的验证码
     *
     * @param param 生成参数
     * @return {@link CaptchaResult}
     * @author luyuhao
     * @since 2021/11/16
     */
    public static CaptchaResult picCaptchaGenerate(CaptchaParam param) throws IOException, FontFormatException {
        // 创建结果对象
        CaptchaResult result = new CaptchaResult();
        // 初始化
        SpecCaptcha captcha = new SpecCaptcha();
        // 设置参数
        if (param.getFont() != null) {
            captcha.setFont(param.getFont());
        }
        // 设置结果
        result.setCode(captcha.text());
        result.setBase64(captcha.toBase64());

        return result;
    }
}
