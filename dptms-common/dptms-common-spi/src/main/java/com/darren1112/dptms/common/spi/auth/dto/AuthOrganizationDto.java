package com.darren1112.dptms.common.spi.auth.dto;

import com.darren1112.dptms.common.spi.auth.entity.AuthOrganizationEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author luyuhao
 * @since 2020/08/16 01:35
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AuthOrganizationDto extends AuthOrganizationEntity {

    /**
     * 是否更新
     */
    @ApiModelProperty("是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @ApiModelProperty("是否已分配")
    private Boolean isAssigned;
}
