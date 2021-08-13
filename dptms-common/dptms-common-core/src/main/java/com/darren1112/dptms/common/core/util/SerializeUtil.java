package com.darren1112.dptms.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化工具
 *
 * @author luyuhao
 * @since 2021/8/13
 */
public class SerializeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 序列化
     *
     * @param object 对象
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/13
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                oos = null;
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                baos = null;
            }
        }
    }

    /**
     * 反序列化
     *
     * @param bytes 待反序列化数组
     * @return {@link Object}
     * @author luyuhao
     * @since 2021/8/13
     */
    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                ois = null;
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                bais = null;
            }
        }
    }
}
