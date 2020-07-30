package com.darren1112.dptms.auth.common.oauth2;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录账号信息
 *
 * @author luyuhao
 * @date 2020/07/26 20:03
 */
@Data
@Accessors(chain = true)
public class SecurityUserInfo implements UserDetails {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限集合
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 是否未过期
     */
    private boolean accountNonExpired = true;

    /**
     * 是否未锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 密码是否未过期
     */
    private boolean credentialsNonExpired = true;

    /**
     * 账号是否可用
     */
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
