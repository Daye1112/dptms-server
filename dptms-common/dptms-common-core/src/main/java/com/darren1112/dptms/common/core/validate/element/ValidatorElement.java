package com.darren1112.dptms.common.core.validate.element;

import com.darren1112.dptms.common.core.validate.validator.ValidatorChain;
import com.darren1112.dptms.common.core.validate.validator.callback.ValidatorCallback;

import java.util.List;

/**
 * @author luyuhao
 * @since 2020/11/28 13:36
 */
public class ValidatorElement {

    private Object target;

    private ValidatorChain chain;

    public ValidatorElement(Object target) {
        this.target = target;
        this.chain = new ValidatorChain();
    }

    public ValidatorChain getChain() {
        return chain;
    }

    public Object getTarget() {
        return target;
    }

    public List<ValidatorCallback> getValidators() {
        return this.chain.getValidators();
    }

    public void addValidators(List<ValidatorCallback> validators) {
        this.chain.addValidators(validators);
    }

    public void addValidators(int index, List<ValidatorCallback> validators) {
        this.chain.addValidators(index, validators);
    }
}
