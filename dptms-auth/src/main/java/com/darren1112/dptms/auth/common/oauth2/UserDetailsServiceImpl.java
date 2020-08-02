package com.darren1112.dptms.auth.common.oauth2;

import com.darren1112.dptms.auth.service.SysUserService;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 自定义用户认证
 *
 * @author luyuhao
 * @date 2020/07/26 19:36
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 自定义登录认证
     *
     * @param username 用户名
     * @return 用户信息
     * @author luyuhao
     * @date 20/07/26 20:07
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDto sysUserDto = sysUserService.getByUsername(username);
        if (Objects.nonNull(sysUserDto)) {
            //设置权限、菜单等信息
        }
        return convertTo(sysUserDto);
    }

    /**
     * 用户信息转换
     *
     * @param sysUserDto 当前用户信息
     * @return security用户信息
     * @author luyuhao
     * @date 20/07/26 20:20
     */
    private SecurityUserInfo convertTo(SysUserDto sysUserDto) {
        SecurityUserInfo securityUserInfo = new SecurityUserInfo();
        if (Objects.isNull(sysUserDto)) {
            return securityUserInfo;
        }
        securityUserInfo.setId(sysUserDto.getId());
        securityUserInfo.setPassword(sysUserDto.getPassword());
        securityUserInfo.setUsername(sysUserDto.getUsername());
        //设置权限
        return securityUserInfo;
    }
}
