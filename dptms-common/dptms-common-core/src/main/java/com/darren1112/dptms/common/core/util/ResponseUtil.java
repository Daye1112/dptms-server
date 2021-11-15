package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.message.JsonResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 响应工具类
 *
 * @author luyuhao
 * @since 2020/08/02 02:47
 */
@Slf4j
public class ResponseUtil {

    /**
     * 设置jsonResult响应
     *
     * @param response   响应域
     * @param jsonResult 响应结果
     * @author luyuhao
     * @since 20/08/02 02:50
     */
    public static void setJsonResult(HttpServletResponse response, JsonResult jsonResult) {
        PrintWriter out = null;
        try {
            String jsonString = JsonUtil.toJsonString(jsonResult);
            response.setContentType(ResponseEntityUtil.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(jsonResult.getCode());
            out = response.getWriter();
            out.write(jsonString);
        } catch (Exception e) {
            if (Objects.nonNull(out)) {
                out.close();
            }
        }
    }
}
