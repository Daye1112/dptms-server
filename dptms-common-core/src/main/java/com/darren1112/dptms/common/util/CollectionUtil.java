package com.darren1112.dptms.common.util;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author luyuhao
 * @date 2020/08/01 03:52
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * 判断集合不为空
     *
     * @param collection 集合
     * @return true/false
     * @author luyuhao
     * @date 20/08/01 03:53
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组为空
     *
     * @param arr 数组
     * @return true/false
     * @author luyuhao
     * @date 20/08/01 03:53
     */
    public static <T> boolean isEmpty(T[] arr) {
        return ArrayUtil.isEmpty(arr);
    }

    /**
     * 判断数组不为空
     *
     * @param arr 数组
     * @return true/false
     * @author luyuhao
     * @date 20/08/01 03:53
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
