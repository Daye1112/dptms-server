package com.darren1112.dptms.sdk.starter.security.core.security.handler;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 异地登录处理策略
 *
 * @author luyuhao
 * @since 2022/06/05
 */
public class SimpleSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);

    /**
     * 异地登录处理逻辑
     *
     * @param event 异地登录事件
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        log.warn("失败信息: 异地登录, ip: {}, 请求接口: {}", IpUtil.getIp(event.getRequest()), event.getRequest().getRequestURI());
        ResponseUtil.writeJson(event.getResponse(), JsonResult.buildErrorEnum(SecurityEnum.REPEAT_LOGIN));
    }
}
