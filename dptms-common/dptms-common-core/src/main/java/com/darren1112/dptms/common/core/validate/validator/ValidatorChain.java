package com.darren1112.dptms.common.core.validate.validator;

import com.darren1112.dptms.common.core.validate.validator.callback.ValidatorCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyuhao
 * @since 2020/11/28 13:37
 */
public class ValidatorChain {

    private List<ValidatorCallback> validators;

    public void setValidators(List<ValidatorCallback> validators) {
        synchronized (this) {
            this.validators = validators;
        }
    }

    public void addValidators(List<ValidatorCallback> validators) {
        synchronized (this) {
            if (null == this.validators) {
                this.validators = new ArrayList<>();
            }
            this.validators.addAll(validators);
        }
    }

    public void doValidate(ValidatorContext context, Object target) {
        for (ValidatorCallback validator : validators) {
            if (!validator.validate(context, target)) {
                context.getValidatorResult().setCheckResult(false);
                return;
            }
        }
    }

    public List<ValidatorCallback> getValidators() {
        return validators;
    }

    public void addValidators(int index, List<ValidatorCallback> validators) {
        synchronized (this) {
            if (null == this.validators) {
                this.validators = new ArrayList<>();
            }
            this.validators.addAll(index, validators);
        }
    }
}
