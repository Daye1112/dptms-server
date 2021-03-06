package com.darren1112.dptms.common.core.util;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 *
 * @author luyuhao
 * @since 2020/08/01 03:52
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * 判断集合不为空
     *
     * @param collection 集合
     * @return true/false
     * @author luyuhao
     * @since 20/08/01 03:53
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组为空
     *
     * @param array 数组
     * @return true/false
     * @author luyuhao
     * @since 20/08/01 03:53
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组不为空
     *
     * @param arr 数组
     * @return true/false
     * @author luyuhao
     * @since 20/08/01 03:53
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 数组转list
     *
     * @param array 数组
     * @return list
     * @author luyuhao
     * @since 2020/8/11 14:24
     */
    public static <T> List<T> arrayToList(T[] array) {
        if (isEmpty(array)) {
            return new ArrayList<>();
        }
        return Arrays.asList(array);
    }
}
