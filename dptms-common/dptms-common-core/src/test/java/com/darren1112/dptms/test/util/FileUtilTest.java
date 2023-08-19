package com.darren1112.dptms.test.util;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 文件工具测试类
 *
 * @author darren
 * @since 2021/12/2
 */
public class FileUtilTest {

    @Test
    public void test01() throws Exception {
        BufferedInputStream inputStream = FileUtil.getInputStream("D:\\soft\\daily\\亿图图示 9.0.zip");
        System.out.println("文件总大小：" + inputStream.available());
        List<InputStream> resultList = com.darren1112.dptms.common.core.util.FileUtil.splitInputStream(inputStream, 10 * 1024 * 1024);
        int index = 0;
        for (InputStream stream : resultList) {
            System.out.println("文件" + (++index) + "大小：" + stream.available());
            FileUtil.writeFromStream(stream, "D:\\data\\desktop\\测试文件\\file\\file_" + index + ".tmp");
        }
    }

    @Test
    public void test02() {
        List<File> files = FileUtil.loopFiles("D:\\data\\desktop\\测试文件\\file\\");
        files.sort((o1, o2) -> {
            String mainName1 = FileUtil.mainName(o1);
            int o1Order = Integer.parseInt(mainName1.split("_")[1]);
            String mainName2 = FileUtil.mainName(o2);
            int o2Order = Integer.parseInt(mainName2.split("_")[1]);
            return Integer.compare(o1Order, o2Order);
        });
        byte[][] byteArr = new byte[files.size()][];
        int index = 0;
        for (File file : files) {
            System.out.println(file.getName());
            byte[] bytes = FileUtil.readBytes(file);
            byteArr[index++]=bytes;
        }
        byte[] bytes = com.darren1112.dptms.common.core.util.FileUtil.mergeBytes(byteArr);
        FileUtil.writeBytes(bytes, "D:\\data\\desktop\\测试文件\\file\\亿图图示 9.0.zip");
    }
}
