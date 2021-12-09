package com.darren1112.dptms.common.core.enums;

import com.darren1112.dptms.common.core.util.StringUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 文件类型枚举
 *
 * @author luyuhao
 * @since 2021/12/3
 */
public enum FileTypeEnum {

    /**
     * 文件类型
     */
    OTHER(1, Collections.emptyList()),
    IMAGE(2, Arrays.asList("bmp", "jpg", "jpeg", "png", "gif")),
    OFFICE(3, Arrays.asList("doc", "docx", "xls", "xlsx", "ppt", "pptx")),
    ;


    /**
     * 类型
     */
    private int type;

    /**
     * 后缀名
     */
    private List<String> extNames;

    FileTypeEnum(int type, List<String> extNames) {
        this.type = type;
        this.extNames = extNames;
    }

    /**
     * 根据文件后缀名匹配文件类型
     *
     * @param extName 文件后缀名
     * @return {@link int}
     * @author luyuhao
     * @since 2021/12/4
     */
    public static int matchTypeByExtName(String extName) {
        if (StringUtil.isEmpty(extName)) {
            return OTHER.getType();
        }
        String lowerExtName = extName.toLowerCase();
        return Arrays.stream(FileTypeEnum.values())
                .filter(item -> item.getExtNames().contains(lowerExtName))
                .findFirst()
                .orElse(OTHER)
                .getType();
    }

    public int getType() {
        return type;
    }

    public List<String> getExtNames() {
        return extNames;
    }
}
