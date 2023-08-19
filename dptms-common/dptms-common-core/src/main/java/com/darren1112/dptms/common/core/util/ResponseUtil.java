package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.message.JsonResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 响应工具类
 *
 * @author darren
 * @since 2020/08/02 02:47
 */
@Slf4j
public class ResponseUtil {

    /**
     * 向响应域中写入jsonResult字符串
     *
     * @param response   响应域
     * @param jsonString jsonResult
     * @author darren
     * @since 2022/03/31
     */
    public static void writeJson(HttpServletResponse response, String jsonString) {
        JsonResult jsonResult = JsonUtil.parseObject(jsonString, JsonResult.class);
        writeJson(response, jsonResult);
    }

    /**
     * 设置jsonResult响应
     *
     * @param response   响应域
     * @param jsonResult 响应结果
     * @author darren
     * @since 20/08/02 02:50
     */
    public static void writeJson(HttpServletResponse response, JsonResult jsonResult) {
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
