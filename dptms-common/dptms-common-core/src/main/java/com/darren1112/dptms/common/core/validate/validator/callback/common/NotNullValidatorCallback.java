package com.darren1112.dptms.common.core.validate.validator.callback.common;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;
import com.darren1112.dptms.common.core.validate.validator.callback.BaseValidatorCallback;

/**
 * @author luyuhao
 * @since 2020/11/28 14:25
 */
public class NotNullValidatorCallback extends BaseValidatorCallback<Object> {

    public NotNullValidatorCallback() {
        super(CommonErrorCodeEnum.OBJECT_NOT_NULL);
    }

    public NotNullValidatorCallback(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum);
    }

    @Override
    protected boolean doValidateInternal(ValidatorContext context, Object target) {
        return target != null;
    }
}
