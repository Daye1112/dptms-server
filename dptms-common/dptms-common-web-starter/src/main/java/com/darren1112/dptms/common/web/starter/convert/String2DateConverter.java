package com.darren1112.dptms.common.web.starter.convert;

import com.darren1112.dptms.common.core.util.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * SpringContext在String和Date时的用的转化器
 *
 * @author luyuhao
 * @since 2020/11/29 00:33
 */
public class String2DateConverter implements Converter<String, Date> {
    @SuppressWarnings("NullableProblems")
    @Override
    public Date convert(String source) {
        return DateUtil.parseDate(source);
    }
}
