package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysOperateLogEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 操作日志Dto
 *
 * @author luyuhao
 * @date 2021/02/06 20:38
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysOperateLogDto extends SysOperateLogEntity {
}
