package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysLoginLogEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 登录日志Dto
 *
 * @author luyuhao
 * @since 2021/02/06 20:38
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysLoginLogDto extends SysLoginLogEntity {
}
