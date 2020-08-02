package com.darren1112.dptms.common.core.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作
 *
 * @author luyuhao
 * @date 19/12/15 14:40
 */
public class StringUtil extends StringUtils {

    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断字符串是否含有中文
     *
     * @param str 待判断的字符串
     * @return true:是; false:否
     */
    public static boolean isContainChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Matcher m = CHINESE_PATTERN.matcher(str);
        return m.find();
    }

    /**
     * 字符串数组转为longList
     *
     * @param strArr 字符串数组
     * @return longList
     */
    public static List<Long> strArrToLongList(String[] strArr) {
        if (CollectionUtil.isEmpty(strArr)) {
            return Collections.emptyList();
        }
        List<Long> res = new ArrayList<>();
        for (String str : strArr) {
            res.add(Long.parseLong(str));
        }
        return res;
    }

    /**
     * 判断对象是否不为空
     *
     * @param str 带判断的对象
     * @return true:不为空;false:为空
     */
    public static boolean isNotEmpty(@Nullable Object str) {
        return !isEmpty(str);
    }

    /**
     * 字符串转为list
     */
    public static List<String> strToList(String str, String splitStr) {
        if (isEmpty(str)) {
            return Collections.emptyList();
        }
        try {
            String[] strSplit = str.split(splitStr);
            return Arrays.asList(strSplit);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * 字符串转为set
     */
    public static Set<String> strToSet(String str, String splitStr) {
        if (isEmpty(str)) {
            return Collections.emptySet();
        }
        try {
            String[] strSplit = str.split(splitStr);
            return new HashSet<>(Arrays.asList(strSplit));
        } catch (Exception e) {
            return Collections.emptySet();
        }
    }

    /**
     * 判断字符串中是否包含子串
     * 无视大小写
     *
     * @param str    字符串
     * @param subStr 子串
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 01:43
     */
    public static boolean containsIgnoreCase(String str, String subStr) {
        if (str == null || subStr == null) {
            return false;
        }
        int len = subStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, subStr, 0, len)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串比较
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 19:52
     */
    public static boolean equal(String str1, String str2) {
        return Objects.equals(str1, str2);
    }

}
