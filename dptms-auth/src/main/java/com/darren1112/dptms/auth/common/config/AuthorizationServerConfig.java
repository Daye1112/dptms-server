package com.darren1112.dptms.auth.common.config;

import com.darren1112.dptms.auth.common.oauth2.ExceptionTranslator;
import com.darren1112.dptms.auth.common.properties.AuthProperties;
import com.darren1112.dptms.auth.common.properties.ClientProperties;
import com.darren1112.dptms.common.exception.BadRequestException;
import com.darren1112.dptms.common.util.CollectionUtil;
import com.darren1112.dptms.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

/**
 * @author luyuhao
 * @date 2020/07/26 20:55
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientProperties[] clientArr = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (CollectionUtil.isNotEmpty(clientArr)) {
            for (ClientProperties clientProperties : clientArr) {
                if (StringUtil.isEmpty(clientProperties.getClient())) {
                    throw new BadRequestException("client不能为空");
                }
                if (StringUtil.isEmpty(clientProperties.getSecret())) {
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = clientProperties.getGrantType().split(",");
                builder.withClient(clientProperties.getClient())
                        .secret(passwordEncoder.encode(clientProperties.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(clientProperties.getScope());
            }
        }
        // clients.inMemory()
        //         .withClient("dptms")
        //         .secret(passwordEncoder.encode("123456"))
        //         .authorizedGrantTypes("password", "refresh_token")
        //         .scopes("all");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);
    }


    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenExpiredSeconds());
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenExpiredSeconds());
        return tokenServices;
    }
}
