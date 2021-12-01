package com.darren1112.dptms.common.spi.file.dto;

import com.darren1112.dptms.common.spi.file.entity.FileDfsInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件存储信息Dto
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileDfsInfoDto extends FileDfsInfoEntity {

    private static final long serialVersionUID = 1L;
}
