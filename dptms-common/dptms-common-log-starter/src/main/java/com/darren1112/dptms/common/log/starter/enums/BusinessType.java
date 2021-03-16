package com.darren1112.dptms.common.log.starter.enums;

import com.darren1112.dptms.common.core.base.BaseCommonEnum;

/**
 * 业务类型
 *
 * @author luyuhao
 * @since 2021/02/02 01:02
 */
public enum BusinessType implements BaseCommonEnum {
    /**
     * 业务类型
     */
    OTHER(0, "其它"),
    INSERT(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    EXPORT(4, "导出"),
    IMPORT(5, "导入"),
    FORCE(6, "强退"),
    QUERY(7, "查询"),
    ;

    private Integer code;
    private String name;

    BusinessType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
