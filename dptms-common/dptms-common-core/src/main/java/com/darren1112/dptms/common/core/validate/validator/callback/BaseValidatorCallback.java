package com.darren1112.dptms.common.core.validate.validator.callback;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;

/**
 * @author luyuhao
 * @since 2020/11/28 14:20
 */
public abstract class BaseValidatorCallback<T> implements ValidatorCallback<T> {

    private BaseErrorEnum baseErrorEnum;

    public BaseValidatorCallback(BaseErrorEnum baseErrorEnum) {
        this.baseErrorEnum = baseErrorEnum;
    }

    @Override
    public boolean validate(ValidatorContext context, T target) {
        boolean result = doValidateInternal(context, target);
        if (result) {
            successHandle(context);
        } else {
            errorHandle(context);
        }
        return result;
    }

    private void errorHandle(ValidatorContext context) {
        context.getValidatorResult().setBaseErrorEnum(this.baseErrorEnum);
    }

    private void successHandle(ValidatorContext context) {
        return;
    }

    protected abstract boolean doValidateInternal(ValidatorContext context, T target);
}
