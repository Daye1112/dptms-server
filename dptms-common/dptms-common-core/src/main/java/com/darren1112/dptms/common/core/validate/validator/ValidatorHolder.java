package com.darren1112.dptms.common.core.validate.validator;


import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.validate.element.ValidatorElement;
import com.darren1112.dptms.common.core.validate.handler.ValidateHandler;
import com.darren1112.dptms.common.core.validate.result.ValidatorResult;
import com.darren1112.dptms.common.core.validate.validator.callback.ValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 校验类上下文
 *
 * @author luyuhao
 * @since 20/01/07 01:04
 */
public class ValidatorHolder {

    private ValidatorContext context;

    private List<ValidatorElement> elements;

    private ValidatorDelegator delegator;

    public ValidatorHolder() {
        this.context = new ValidatorContext();
        this.elements = new LinkedList<>();
        this.delegator = new ValidatorDelegator();
    }

    public ValidatorHolder on(Object target) {
        ValidatorElement element = new ValidatorElement(target);
        if (needCreateDefaultValidatorCallback(element.getValidators())) {
            element.addValidators(0, createDefaultValidatorCallback());
        }
        elements.add(element);
        return this;
    }

    public ValidatorHolder on(Object target, ValidatorCallback... validators) {
        ValidatorElement element = new ValidatorElement(target);
        element.addValidators(Arrays.asList(validators));
        if (needCreateDefaultValidatorCallback(element.getValidators())) {
            element.addValidators(0, createDefaultValidatorCallback());
        }
        elements.add(element);
        return this;
    }

    private List<ValidatorCallback> createDefaultValidatorCallback() {
        return Collections.singletonList(new NotNullValidatorCallback());
    }

    private boolean needCreateDefaultValidatorCallback(List<ValidatorCallback> validators) {
        return CollectionUtil.isEmpty(validators);
    }

    public ValidatorHolder doValidate() {
        delegator.doValidate(context, elements);
        return this;
    }

    public ValidatorResult result() {
        return context.getValidatorResult();
    }

    public void checkResult() {
        ValidateHandler.checkValidator(this.context.getValidatorResult());
    }
}
