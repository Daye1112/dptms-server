package com.darren1112.dptms.auth.common.properties;

import lombok.Data;

/**
 * 客户端Properties
 *
 * @author luyuhao
 * @date 2020/08/01 03:42
 */
@Data
public class ClientProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
