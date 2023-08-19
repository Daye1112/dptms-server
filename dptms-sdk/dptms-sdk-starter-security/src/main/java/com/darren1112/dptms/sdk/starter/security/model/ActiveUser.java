package com.darren1112.dptms.sdk.starter.security.model;

import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 活跃(在线)用户信息
 *
 * @author darren
 * @since 2022/11/15
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ActiveUser extends BaseSecurityUser {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realName;

    /**
     * 性别 1：男 2：女
     */
    @ApiModelProperty("性别 1：男 2：女")
    private Integer gender;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 密码更新时间
     */
    @ApiModelProperty("密码更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pwdUpdateTime;

    /**
     * 头像文件id
     */
    @ApiModelProperty("头像文件id")
    private Long fileId;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    /**
     * 浏览器
     */
    @ApiModelProperty("浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String os;

    /**
     * ip
     */
    @ApiModelProperty("ip")
    private String ip;

    /**
     * ip地址
     */
    @ApiModelProperty("ip地址")
    private String ipAddress;

    /**
     * 对象属性复制
     *
     * @param sysUserDto 用户信息
     * @return {@link ActiveUser}
     * @author darren
     * @since 2021/01/31 19:50
     */
    public static ActiveUser convert(AuthUserDto sysUserDto) {
        ActiveUser activeUser = new ActiveUser();
        convert(activeUser, sysUserDto);
        return activeUser;
    }

    /**
     * 对象属性复制
     *
     * @param activeUser 用户信息
     * @param sysUserDto 新用户信息
     * @author darren
     * @since 2021/01/31 19:50
     */
    public static void convert(ActiveUser activeUser, AuthUserDto sysUserDto) {
        activeUser.setId(sysUserDto.getId());
        activeUser.setUserId(sysUserDto.getId());
        activeUser.setUsername(sysUserDto.getUsername());
        activeUser.setRealName(sysUserDto.getRealName());
        activeUser.setGender(sysUserDto.getGender());
        activeUser.setPhoneNumber(sysUserDto.getPhoneNumber());
        activeUser.setEmail(sysUserDto.getEmail());
        activeUser.setLastLoginTime(sysUserDto.getLastLoginTime());
        activeUser.setFileId(sysUserDto.getFileId());
        activeUser.setPwdUpdateTime(sysUserDto.getPwdUpdateTime());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
