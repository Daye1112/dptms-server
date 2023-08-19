package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.constants.FileConstant;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author darren
 * @since 2020/11/23 00:18
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建系统文件
     *
     * @param ins  文件流
     * @param name 文件名
     * @return {@link File}
     * @author darren
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

    /**
     * 文件分割
     *
     * @param in   文件输入流
     * @param size 切割大小
     * @return {@link InputStream}
     * @author darren
     * @since 2021/12/2
     */
    public static List<InputStream> splitInputStream(InputStream in, int size) throws Exception {
        // 初始化结果集合
        List<InputStream> result = new ArrayList<>();
        // 获取文件大小
        int fileSize = in.available();
        // 若文件大小小于切割大小，直接返回
        if (fileSize <= size) {
            result.add(in);
            return result;
        }
        // 计算参数
        int count = (fileSize + size - 1) / size;
        int byteSize = size > 1024 ? 1024 : size;

        // 循环遍历
        for (int i = 0; i < count; i++) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                // 初始化分割参数
                byte[] bytes = new byte[byteSize];
                int splitCount = 0;
                int len;
                while (splitCount < size && (len = in.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                    baos.flush();
                    splitCount += len;
                }
                result.add(new ByteArrayInputStream(baos.toByteArray()));
            }
        }
        return result;
    }

    /**
     * 合并byte数组
     *
     * @param bytes byte数组
     * @return {@link byte[]}
     * @author darren
     * @since 2021/12/2
     */
    public static byte[] mergeBytes(byte[]... bytes) {
        byte[] result = null;
        for (byte[] subByte : bytes) {
            result = ArrayUtils.addAll(result, subByte);
        }
        return result;
    }
}
