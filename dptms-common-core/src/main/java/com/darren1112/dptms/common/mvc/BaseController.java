package com.darren1112.dptms.common.mvc;


import com.darren1112.dptms.spi.common.dto.PageParam;

/**
 * 基础controller类，包含controller通用方法
 *
 * @author luyuhao
 * @since 20/01/26 16:14
 */
public class BaseController {

    protected PageParam setPageParam(PageParam pageParam) {
        if (pageParam.getCurrentPage() != null && pageParam.getPageSize() != null) {
            pageParam.setStartIndex((pageParam.getCurrentPage() - 1) * pageParam.getPageSize());
        }
        return pageParam;
    }

}
