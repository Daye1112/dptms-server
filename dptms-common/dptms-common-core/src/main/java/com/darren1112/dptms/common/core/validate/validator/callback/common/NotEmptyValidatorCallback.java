package com.darren1112.dptms.common.core.validate.validator.callback.common;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.validate.validator.ValidatorContext;
import com.darren1112.dptms.common.core.validate.validator.callback.BaseValidatorCallback;

/**
 * @author luyuhao
 * @since 2020/11/28 14:41
 */
public class NotEmptyValidatorCallback extends BaseValidatorCallback<String> {

    public NotEmptyValidatorCallback() {
        super(CommonErrorCodeEnum.STRING_NOT_EMPTY);
    }

    public NotEmptyValidatorCallback(BaseEnum baseEnum) {
        super(baseEnum);
    }

    @Override
    protected boolean doValidateInternal(ValidatorContext context, String target) {
        return StringUtil.isNotEmpty(target);
    }
}
