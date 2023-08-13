package com.darren1112.dptms.common.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 字符串操作
 *
 * @author luyuhao
 * @since 19/12/15 14:40
 */
public class StringUtil extends StringUtils {

    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 默认分隔符
     */
    private static final String DEFUALT_DELIMITER = ",";

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
     * @since 20/08/02 01:43
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
     * @since 20/08/02 19:52
     */
    public static boolean equal(String str1, String str2) {
        return Objects.equals(str1, str2);
    }

    /**
     * 字符串比较，无视大小写
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return true/false
     * @author luyuhao
     * @since 2020/8/7 14:43
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * 字符串分隔为set集合，逗号分隔
     *
     * @param str 字符串
     * @return {@link String}
     * @author luyuhao
     * @since 2021/7/22
     */
    public static Set<String> splitToSet(String str) {
        return splitToSet(str, DEFUALT_DELIMITER);
    }

    /**
     * 根据分隔符，将字符串转为set集合
     *
     * @param str       字符串
     * @param delimiter 分隔符
     * @return {@link String}
     * @author luyuhao
     * @since 2021/7/22
     */
    private static Set<String> splitToSet(String str, String delimiter) {
        if (isBlank(str)) {
            return new HashSet<>();
        }
        return Arrays.stream(split(str, delimiter)).collect(Collectors.toSet());
    }

    /**
     * 字符串是否为空
     *
     * @param str 待校验串
     * @return {@link boolean}
     * @author luyuhao
     * @since 2022/11/15
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串格式化
     * <p>
     * input ==>
     * "aaa {} bbb {} ccc", "111", "222"
     * result <==
     * "aaa 111 bbb 222 ccc"
     *
     * @param pattern 占位符字符串
     * @param args    占位属性
     * @return {@link String}
     * @author darren
     * @since 2023/08/13
     */
    public static String format(String pattern, Object... args) {
        FormattingTuple ft = MessageFormatter.arrayFormat(pattern, args);
        return ft.getMessage();
    }
}
