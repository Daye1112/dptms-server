package com.darren1112.dptms.common.core.validate.result;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;

/**
 * @author luyuhao
 * @since 2020/11/28 13:32
 */
public class ValidatorResult {

    /**
     * 校验结果
     */
    private boolean checkResult;

    /**
     * 错误信息枚举
     */
    private BaseEnum baseEnum;

    public ValidatorResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public BaseEnum getBaseEnum() {
        return baseEnum;
    }

    public void setBaseEnum(BaseEnum baseEnum) {
        this.baseEnum = baseEnum;
    }
}
