package com.darren1112.dptms.common.util;

import com.darren1112.dptms.common.message.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应工具类
 *
 * @author luyuhao
 * @date 2020/08/02 02:47
 */
public class ResponseUtil {

    /**
     * 设置jsonResult响应
     *
     * @param response   响应域
     * @param jsonResult 响应结果
     * @author luyuhao
     * @date 20/08/02 02:50
     */
    public static void setJsonResult(HttpServletResponse response, JsonResult jsonResult) throws IOException {
        String jsonString = JsonUtil.toJsonString(jsonResult);
        response.setContentType(ResponseEntityUtil.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(jsonResult.getCode());
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.close();
    }
}
