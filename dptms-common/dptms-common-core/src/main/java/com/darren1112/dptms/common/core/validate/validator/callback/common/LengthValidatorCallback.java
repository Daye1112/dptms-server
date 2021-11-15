package com.darren1112.dptms.common.core.validate.validator.callback.common;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;
import com.darren1112.dptms.common.core.validate.validator.callback.BaseValidatorCallback;

/**
 * 字符串长度校验
 *
 * @author luyuhao
 * @since 2021/7/21
 */
public class LengthValidatorCallback extends BaseValidatorCallback<String> {

    /**
     * 最大长度
     */
    private Integer maxLength;

    public LengthValidatorCallback() {
        super(CommonErrorCodeEnum.STRING_TOO_LONG);
    }

    public LengthValidatorCallback(BaseErrorEnum baseErrorEnum, Integer maxLength) {
        super(baseErrorEnum);
        this.maxLength = maxLength;
    }

    @Override
    protected boolean doValidateInternal(ValidatorContext context, String target) {
        int strLen = target == null ? 0 : target.length();
        return strLen <= maxLength;
    }
}
