package com.darren1112.dptms.common.core.validate.validator.callback;

import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;

/**
 * @author darren
 * @since 2020/11/28 13:38
 */
public interface ValidatorCallback<T> {

    boolean validate(ValidatorContext context, T target);
}
