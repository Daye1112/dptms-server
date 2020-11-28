package com.darren1112.dptms.common.core.validate.validator.callback.common;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
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

    public NotNullValidatorCallback(BaseEnum baseEnum) {
        super(baseEnum);
    }

    @Override
    protected boolean doValidateInternal(ValidatorContext context, Object target) {
        return target != null;
    }
}
