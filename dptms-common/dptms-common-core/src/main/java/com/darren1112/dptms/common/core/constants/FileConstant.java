package com.darren1112.dptms.common.core.constants;

import java.io.File;

/**
 * 文件常量
 *
 * @author luyuhao
 * @since 2020/11/29 01:50
 */
public class FileConstant {

    /**
     * 静态资源
     */
    public static final String[] STATIC_PATTERNS = {"/webjars/**", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.ico", "/**/*.png", "/**/*.svg"};

    /**
     * 系统文件路径
     */
    public static final String SYSTEM_FILE_PATH = "." + File.separator + "systemFile" + File.separator;
}
