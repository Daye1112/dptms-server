package com.darren1112.dptms.common.core.base;

import com.darren1112.dptms.common.core.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * aop基础类
 *
 * @author darren
 * @since 2021/02/06 23:29
 */
public class BaseAop {

    /**
     * 获取参数
     *
     * @param joinPoint 切点
     * @return {@link String 参数json格式}
     * @author darren
     * @since 2021/02/06 23:34
     */
    protected static String getParams(JoinPoint joinPoint) {
        Map<String, Object> paramMap = new HashMap<>();
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                if (argNames[i].contains("request")
                        || argNames[i].contains("response")
                        || argValues[i] instanceof MultipartFile) {
                    continue;
                }
                paramMap.put(argNames[i], argValues[i]);
            }
        }
        return JsonUtil.toJsonString(paramMap);
    }
}
