package com.darren1112.dptms.common.core.util;

import java.util.List;
import java.util.function.Consumer;

/**
 * 数据库工具类
 *
 * @author darren
 * @since 2022/11/19
 */
public class DatabaseUtil {

    private static final int MAX_HANDLE_NUM = 300;

    /**
     * 分批处理数据
     *
     * @param list            数据集合
     * @param batchHandleFunc 处理方法
     * @author darren
     * @since 2022/11/19
     */
    public static <T> void batchHandle(List<T> list, Consumer<List<T>> batchHandleFunc) {
        batchHandle(MAX_HANDLE_NUM, list, batchHandleFunc);
    }

    /**
     * 分批处理数据
     *
     * @param maxNum          最大处理数
     * @param list            数据集合
     * @param batchHandleFunc 处理方法
     * @author darren
     * @since 2022/11/19
     */
    public static <T> void batchHandle(int maxNum, List<T> list, Consumer<List<T>> batchHandleFunc) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        // 处理数量
        int handleNum = maxNum;
        if (list.size() > maxNum) {
            // 若记录数大于最大处理数，则分批处理
            int size = (list.size() - 1) / handleNum + 1;
            int remainder = list.size() % handleNum;
            // 分批处理
            for (int i = 0; i < size; i++) {
                // 若是最后一批，且最后一批的除余大于0，说明最后一批不足最大值，则用除余结果进行处理
                if ((i == size - 1) && remainder > 0) {
                    handleNum = remainder;
                }
                batchHandleFunc.accept(list.subList(i * maxNum, i * maxNum + handleNum));
            }
        } else {
            // 若记录数小于最大插入数，一次性入库
            batchHandleFunc.accept(list);
        }
    }
}
