package com.darren1112.dptms.file.test.service;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import com.darren1112.dptms.common.fastdfs.starter.core.client.FileClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * fastdfs测试类
 *
 * @author luyuhao
 * @since 2021/11/30
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FastDfsServiceTest {

    @Autowired
    private FileClient fileClient;

    @BeforeClass
    public static void init() {
        EnvironmentAwareUtil.adjust();
    }

    @Test
    public void test01() {
    }
}
