package com.darren1112.dptms.common.spi.sys.dto;

import lombok.Data;

/**
 * redis dto
 *
 * @author luyuhao
 * @date 2021/01/31 00:02
 */
@Data
public class SysRedisDto {

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private Object value;

    /**
     * 有效期
     */
    private Long expired;
}
