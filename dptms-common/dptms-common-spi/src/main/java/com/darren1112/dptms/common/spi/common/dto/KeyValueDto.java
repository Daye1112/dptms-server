package com.darren1112.dptms.common.spi.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * key-value对象
 *
 * @author luyuhao
 * @date 2021/02/17 22:40
 */
@Data
@ApiModel("key-value对象")
public class KeyValueDto {

    @ApiModelProperty("key")
    private Integer key;

    @ApiModelProperty("value")
    private Object value;
}
