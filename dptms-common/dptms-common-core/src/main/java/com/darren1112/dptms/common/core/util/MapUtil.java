package com.darren1112.dptms.common.core.util;

import java.util.Map;

/**
 * map工具类
 *
 * @author darren
 * @since 2022/06/12
 */
public class MapUtil {

    /**
     * 判断map不为空
     *
     * @param map map
     * @return true/false
     * @author darren
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
     * @author darren
     * @since 2021/8/6
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
