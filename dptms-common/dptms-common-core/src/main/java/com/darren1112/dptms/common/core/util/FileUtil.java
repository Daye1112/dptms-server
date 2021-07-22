package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.constants.FileConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 文件工具类
 *
 * @author luyuhao
 * @since 2020/11/23 00:18
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建系统文件
     *
     * @param ins  文件流
     * @param name 文件名
     * @return {@link File}
     * @author luyuhao
     * @since 2021/7/22
     */
    public static File createSystemFile(InputStream ins, String name) {
        OutputStream os = null;
        ByteArrayOutputStream output = null;
        try {
            File dir = new File(FileConstant.SYSTEM_FILE_PATH);
            if (!dir.exists()) {
                boolean mkdirsResult = dir.mkdirs();
            }
            File file = new File(FileConstant.SYSTEM_FILE_PATH, name);
            if (file.exists()) {
                return file;
            }

            os = new FileOutputStream(file);
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n;
            while ((n = ins.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
            os.write(output.toByteArray());
            return file;
        } catch (Exception e) {
            LOGGER.error("创建系统文件 " + name + " 异常", e);
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常", e);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常", e);
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常", e);
                }
            }
        }
    }

}
