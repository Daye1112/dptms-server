package com.darren1112.dptms.sdk.starter.security.core.token.manager.base;

import com.darren1112.dptms.common.core.base.BaseBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 管理器-抽象类
 *
 * @author darren
 * @since 2022/11/17
 */
public abstract class BaseManager implements BaseBean, ApplicationContextAware, EnvironmentAware {

    protected ApplicationContext applicationContext;

    protected Environment environment;

    @Override
    @SuppressWarnings("NullableProblems")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
