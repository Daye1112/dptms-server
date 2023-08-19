package com.darren1112.dptms.sdk.starter.security.core.token.generator;

import com.darren1112.dptms.common.core.util.UuidUtil;
import com.darren1112.dptms.sdk.starter.security.core.token.generator.base.TokenGenerator;

/**
 * token生成类
 *
 * @author darren
 * @since 2021/01/16 17:10
 */
public class UuidTokenGenerator implements TokenGenerator {

    /**
     * 生成默认token
     *
     * @return {@link String token}
     * @author darren
     * @since 2021/01/16 17:11
     */
    @Override
    public <T> String generate(T param) {
        return UuidUtil.generateDefault();
    }
}
