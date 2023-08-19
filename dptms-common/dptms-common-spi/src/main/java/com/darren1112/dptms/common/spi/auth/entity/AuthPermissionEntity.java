package com.darren1112.dptms.common.spi.auth.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限Entity
 *
 * @author darren
 * @since 20/12/09 23:41
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AuthPermissionEntity extends BaseEntity {

    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名")
    private String perName;

    /**
     * 权限码
     */
    @ApiModelProperty(value = "权限码")
    private String perCode;

    /**
     * 权限组
     */
    @ApiModelProperty(value = "权限组")
    private String perGroup;

    /**
     * 权限url
     */
    @ApiModelProperty(value = "权限url")
    private String perUrl;

}
