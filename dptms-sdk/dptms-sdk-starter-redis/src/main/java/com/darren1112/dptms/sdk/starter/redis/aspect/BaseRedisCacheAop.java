package com.darren1112.dptms.sdk.starter.redis.aspect;

import com.darren1112.dptms.common.core.base.BaseAop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * redis缓存aop基础类
 *
 * @author darren
 * @since 2023/8/25
 */
public class BaseRedisCacheAop extends BaseAop {

    /**
     * 根据类、方法和参数生成缓存key
     *
     * @param joinPoint 切点
     * @return {@link String}
     * @author darren
     * @since 2023/8/25
     */
    public String keyGenerator(ProceedingJoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getTarget().getClass().getSimpleName());
        sb.append(":");
        sb.append(((MethodSignature) joinPoint.getSignature()).getMethod().getName());
        int index = 0;
        //参数值
        Object[] params = joinPoint.getArgs();
        for (Object obj : params) {
            if (index == 0) {
                sb.append(":");
            } else {
                sb.append("_");
            }
            try {
                sb.append(obj.hashCode());
            } catch (Exception e) {
                sb.append(obj);
            }
            index++;
        }
        return sb.toString();
    }
}
