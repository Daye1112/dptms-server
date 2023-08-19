package com.darren1112.dptms.common.core.validate.result;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;

/**
 * @author darren
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
    private BaseErrorEnum baseErrorEnum;

    public ValidatorResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public BaseErrorEnum getBaseErrorEnum() {
        return baseErrorEnum;
    }

    public void setBaseErrorEnum(BaseErrorEnum baseErrorEnum) {
        this.baseErrorEnum = baseErrorEnum;
    }
}
