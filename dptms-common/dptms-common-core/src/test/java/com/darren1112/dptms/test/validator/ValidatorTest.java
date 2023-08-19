package com.darren1112.dptms.test.validator;

import com.darren1112.dptms.common.core.enums.CommonErrorCodeEnum;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import org.junit.Test;

/**
 * @author darren
 * @since 2020/11/28 14:40
 */
public class ValidatorTest {

    @Test
    public void test01() {
        Object obj1 = null;
        String str1 = "123";
        String str2 = "";
        ValidatorBuilder.build()
                .on(obj1)
                .on(str1, new NotEmptyValidatorCallback(CommonErrorCodeEnum.STRING_NOT_EMPTY))
                .on(str2, new NotEmptyValidatorCallback())
                .doValidate().checkResult();
    }
}
