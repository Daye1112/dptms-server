package com.darren1112.dptms.common.log.starter.aspect;

import com.darren1112.dptms.common.core.base.BaseAop;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.RequestUtil;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.collect.LogCollectService;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.log.starter.properties.LogProperties;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * 操作日志aop
 *
 * @author luyuhao
 * @since 2021/02/06 22:31
 */
@Aspect
@Slf4j
public class LogAspect extends BaseAop {

    private LogProperties logProperties;

    private LogCollectService logCollectService;

    public LogAspect(LogProperties logProperties, LogCollectService logCollectService) {
        this.logProperties = logProperties;
        this.logCollectService = logCollectService;
    }

    /**
     * 开始时间
     */
    private long currentTime = 0L;

    /**
     * 配置切入点
     *
     * @author luyuhao
     * @since 2021/02/06 22:32
     */
    @Pointcut("@annotation(com.darren1112.dptms.common.log.starter.annotation.Log)")
    public void pointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法pointcut()上注册的切入点
     *
     * @param joinPoint 切入点
     * @return {@link Object}
     * @author luyuhao
     * @since 2021/02/06 22:33
     */
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取日志注解
        Log logAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Log.class);
        currentTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 是否需要采集
        if (logAnnotation.logLevel().getCode() >= logProperties.getLogLevel().getCode()) {
            // 创建日志对象
            SysOperateLogDto dto = buildDto(joinPoint, logAnnotation, null);
            // 日志收集
            logCollectService.operateLogCollect(dto);
        }
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint 切点
     * @param e         异常
     * @author luyuhao
     * @since 2021/02/06 22:41
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 获取日志注解
        Log logAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Log.class);
        SysOperateLogDto dto = buildDto(joinPoint, logAnnotation, e);
        logCollectService.operateLogCollect(dto);
    }

    /**
     * 创建日志对象
     *
     * @param logAnnotation 日志注解
     * @return {@link SysOperateLogDto}
     * @author luyuhao
     * @since 2021/02/07 00:41
     */
    private SysOperateLogDto buildDto(JoinPoint joinPoint, Log logAnnotation, Throwable e) {
        // 获取请求域、用户信息等
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        ActiveUser activeUser = DptmsSecurityUtil.get();
        // 初始化对象
        SysOperateLogDto dto = new SysOperateLogDto();
        // 用户名
        dto.setUsername(activeUser.getUsername());
        // 操作内容
        dto.setContent(logAnnotation.value());
        // 请求地址
        dto.setUrl(request.getRequestURI());
        // 浏览器
        dto.setBrowser(activeUser.getBrowser());
        // ip
        dto.setIp(activeUser.getIp());
        // ip地址
        dto.setIpAddress(activeUser.getIpAddress());
        // 日志级别
        dto.setLogLevel(e == null ? logAnnotation.logLevel().getCode() : LogLevel.ERROR.getCode());
        // 业务类型
        dto.setBusinessType(logAnnotation.businessType().getCode());
        // 异常内容
        dto.setExceptionContent(Optional.ofNullable(e).map(Throwable::getMessage).orElse(null));
        // 请求参数
        dto.setRequestParam(getParams(joinPoint));
        // 接口耗时
        dto.setTimeConsuming((int) (System.currentTimeMillis() - currentTime));
        // 创建者、更新者
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        // 创建时间、更新时间
        dto.setCtime(new Date());
        dto.setMtime(new Date());
        return dto;
    }
}
