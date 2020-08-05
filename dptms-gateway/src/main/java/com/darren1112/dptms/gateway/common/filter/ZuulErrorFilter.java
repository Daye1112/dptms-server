package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.gateway.common.util.ZuulRequestUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

/**
 * 网关异常拦截处理
 *
 * @author luyuhao
 * @since 2019/12/19 9:35
 */
@Slf4j
@Component
public class ZuulErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return ZuulRequestUtil.getRequestContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = ZuulRequestUtil.getRequestContext();
            if (ctx.getThrowable() != null) {
                log.error("请求失败，失败原因：" + ctx.getThrowable().getMessage());
                ctx.setSendZuulResponse(false);
                //阻止SendErrorFilter
                ctx.set(SEND_ERROR_FILTER_RAN, true);
                //设置错误信息
                errorInfo();
            }
        } catch (Exception e) {
            log.error("zuul异常处理异常", e);
        }
        return null;
    }

    /**
     * 访问异常时进行response处理，给予提示
     */
    private void errorInfo() throws IOException {
        ResponseUtil.setJsonResult(ZuulRequestUtil.getResponse(), JsonResult.buildErrorEnum(MicroErrorCodeEnum.SERVER_DOWN));
    }
}
