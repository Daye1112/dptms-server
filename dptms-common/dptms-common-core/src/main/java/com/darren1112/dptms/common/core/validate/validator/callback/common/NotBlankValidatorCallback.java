package com.darren1112.dptms.common.core.validate.validator.callback.common;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;
import com.darren1112.dptms.common.core.validate.validator.callback.BaseValidatorCallback;

/**
 * string非空校验
 *
 * @author luyuhao
 * @since 2020/12/13 22:26
 */
public class NotBlankValidatorCallback extends BaseValidatorCallback<String> {

    public NotBlankValidatorCallback() {
        super(CommonErrorCodeEnum.STRING_NOT_EMPTY);
    }

    public NotBlankValidatorCallback(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum);
    }

    @Override
    protected boolean doValidateInternal(ValidatorContext context, String target) {
        return StringUtil.isNotEmpty(target);
    }
}
