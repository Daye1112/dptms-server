package com.darren1112.dptms.auth.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus数据填充处理
 *
 * @author luyuhao
 * @since 2022/11/18
 */
@Component
public class DptmsMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "isvalid", Boolean.TRUE);
        this.fillStrategy(metaObject, "ctime", new Date());
        this.fillStrategy(metaObject, "mtime", new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "mtime", new Date());
    }
}
