package com.darren1112.dptms.common.core.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author luyuhao
 * @since 2020/08/01 03:52
 */
public class CollectionUtil extends CollectionUtils {

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
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /**
     * 数组转set
     *
     * @param array 数组
     * @return {@link Set}
     * @author luyuhao
     * @since 2021/7/22
     */
    public static <T> Set<T> arrayToSet(T[] array) {
        if (isEmpty(array)) {
            return new HashSet<>();
        }
        return Arrays.stream(array).collect(Collectors.toSet());
    }

    /**
     * 判断map不为空
     *
     * @param map map
     * @return true/false
     * @author luyuhao
     * @since 2021/8/6
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断map为空
     *
     * @param map map
     * @return true/false
     * @author luyuhao
     * @since 2021/8/6
     */
    private static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 将单个对象封装为集合
     *
     * @param obj 单个对象
     * @return {@link List}
     * @author luyuhao
     * @since 2021/12/4
     */
    public static <T> List<T> packToList(T obj) {
        List<T> result = new ArrayList<>();
        result.add(obj);
        return result;
    }
}
