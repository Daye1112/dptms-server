package com.darren1112.dptms.sdk.starter.security.core.token.manager;

import com.darren1112.dptms.common.core.util.MapUtil;
import com.darren1112.dptms.sdk.starter.security.core.token.manager.base.BaseManager;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.base.TokenValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * token校验器管理者
 *
 * @author luyuhao
 * @since 2022/11/17
 */
@Slf4j
public class TokenValidatorManager extends BaseManager {

    private List<TokenValidator> tokenValidatorList = new ArrayList<>();

    /**
     * 初始方法
     *
     * @throws Exception 异常
     * @author luyuhao
     * @since 2022/11/17
     */
    @Override
    public void init() throws Exception {
        Map<String, TokenValidator> beanMap = applicationContext.getBeansOfType(TokenValidator.class);
        if (MapUtil.isEmpty(beanMap)) {
            log.info("无token校验器");
            return;
        }
        for (Map.Entry<String, TokenValidator> entry : beanMap.entrySet()) {
            tokenValidatorList.add(entry.getValue());
            log.info("token处理器 {} 加载成功", entry.getKey());
        }
        tokenValidatorList.sort(Comparator.comparing(TokenValidator::getOrder));
    }

    public List<TokenValidator> getTokenValidatorList() {
        return tokenValidatorList;
    }
}
