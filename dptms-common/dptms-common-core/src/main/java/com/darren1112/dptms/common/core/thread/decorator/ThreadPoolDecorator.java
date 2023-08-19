package com.darren1112.dptms.common.core.thread.decorator;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 线程池修饰器
 * 传递上下文
 *
 * @author darren
 * @since 2021/2/8 10:50
 */
public class ThreadPoolDecorator implements TaskDecorator {

    @SuppressWarnings("NullableProblems")
    @Override
    public Runnable decorate(Runnable runnable) {
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                runnable.run();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
