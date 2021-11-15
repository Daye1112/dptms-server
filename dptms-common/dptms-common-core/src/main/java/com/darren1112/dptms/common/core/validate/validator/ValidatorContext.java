package com.darren1112.dptms.common.core.validate.validator;

import com.darren1112.dptms.common.core.validate.result.ValidatorResult;

/**
 * 校验类context
 *
 * @author luyuhao
 * @since 20/01/07 01:05
 */
public class ValidatorContext {

    private ValidatorResult validatorResult;

    public ValidatorContext() {
        this.validatorResult = new ValidatorResult(true);
    }

    public ValidatorResult getValidatorResult() {
        return validatorResult;
    }

    public void setValidatorResult(ValidatorResult validatorResult) {
        this.validatorResult = validatorResult;
    }
}
