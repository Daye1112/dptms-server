package com.darren1112.dptms.common.core.validate.validator;

import com.darren1112.dptms.common.core.validate.element.ValidatorElement;

import java.util.List;

/**
 * @author darren
 * @since 2020/11/28 13:47
 */
public class ValidatorDelegator {

    public void doValidate(ValidatorContext context, List<ValidatorElement> elements) {
        for (ValidatorElement element : elements) {
            element.getChain().doValidate(context, element.getTarget());
            if (!context.getValidatorResult().isCheckResult()) {
                break;
            }
        }
    }

}
