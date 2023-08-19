package com.darren1112.dptms.sdk.starter.security.core.token.generator.base;


/**
 * token生成器
 *
 * @author darren
 * @since 2022/6/6
 */
public interface TokenGenerator {

    /**
     * 生成token
     *
     * @param param 参数
     * @return {@link String}
     * @author darren
     * @since 2022/6/6
     */
    <T> String generate(T param);
}
