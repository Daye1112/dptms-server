package com.darren1112.dptms.common.core.validate.validator;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 校验类context
 *
 * @author luyuhao
 * @date 20/01/07 01:05
 */
@Data
@AllArgsConstructor
public class ValidatorContext {

    /**
     * 校验结果
     */
    private Boolean checkResult;

    /**
     * 错误信息枚举
     */
    private BaseEnum baseEnum;

}
