package com.darren1112.dptms.common.core.validate.handler;


import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.validate.validator.ValidatorHolder;

/**
 * 校验处理器
 *
 * @author luyuhao
 * @date 2020/1/7 19:17
 */
public class ValidateHandler {

    /**
     * 判断校验是否成功，若存在错误，抛出异常
     * 针对ValidatorHolder
     */
    public static void checkValidator(ValidatorHolder validator){
        if(! validator.getResult()){
            throw new BadRequestException(validator.getBaseEnum());
        }
    }

    /**
     * 针对单参数校验
     *
     * @param checkResult true:参数错误; false:参数正确
     * @param baseEnum 错误码
     */
    public static void checkParameter(Boolean checkResult, BaseEnum baseEnum){
        if(checkResult){
            throw new BadRequestException(baseEnum);
        }
    }

    /**
     * 针对单参数校验 - 空值校验
     *
     * @param checkObj 待校验的参数
     * @param baseEnum 错误码
     */
    public static void checkNull(Object checkObj, BaseEnum baseEnum){
        if(StringUtil.isEmpty(checkObj)){
            throw new BadRequestException(baseEnum);
        }
    }
}
