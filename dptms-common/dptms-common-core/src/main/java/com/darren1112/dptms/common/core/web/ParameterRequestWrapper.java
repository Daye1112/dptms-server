package com.darren1112.dptms.common.core.web;

import com.darren1112.dptms.common.core.util.MapUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数重新封装
 *
 * @author luyuhao
 * @since 2022/06/12
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 请求参数
     */
    private Map<String, String[]> params = new HashMap<>();

    /**
     * 构造方法
     *
     * @param request 请求域
     * @author luyuhao
     * @since 2022/06/12
     */
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 构造方法
     *
     * @param request 请求域
     * @author luyuhao
     * @since 2022/06/12
     */
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extParams) {
        super(request);
        this.params.putAll(request.getParameterMap());
        this.addAllParameters(extParams);
    }

    /**
     * 获取string请求参数
     *
     * @param name 参数key
     * @return {@link String}
     * @author luyuhao
     * @since 2022/06/12
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 获取参数names
     *
     * @return {@link Enumeration}
     * @author luyuhao
     * @since 2022/06/12
     */
    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    /**
     * 获取所有请求参数
     *
     * @return {@link Map}
     * @author luyuhao
     * @since 2022/06/12
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    /**
     * 获取string数组类型参数值
     *
     * @param name 参数key
     * @return {@link String}
     * @author luyuhao
     * @since 2022/06/12
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        return (values == null || values.length < 1) ? null : values;
    }

    /**
     * 添加参数和参数值
     *
     * @param extParams 额外参数
     * @author luyuhao
     * @since 2022/06/12
     */
    public void addAllParameters(Map<String, Object> extParams) {
        if (MapUtil.isEmpty(extParams)) {
            return;
        }
        for (Map.Entry<String, Object> entry : extParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 将添加的参数名和参数值存放到params中
     *
     * @param name  参数key
     * @param value 参数值
     * @author luyuhao
     * @since 2022/06/12
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}
