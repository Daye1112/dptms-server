package com.darren1112.dptms.common.core.base;


import com.darren1112.dptms.common.spi.common.dto.PageParam;

/**
 * 基础controller类，包含controller通用方法
 *
 * @author luyuhao
 * @since 20/01/26 16:14
 */
public class BaseController {

    /**
     * 设置分页参数
     *
     * @param pageParam 分页参数
     * @return {@link PageParam 分页参数}
     * @author luyuhao
     * @date 20/12/10 01:20
     */
    protected PageParam getPageParam(PageParam pageParam) {
        if (pageParam.getCurrentPage() != null && pageParam.getPageSize() != null) {
            pageParam.setStartIndex((pageParam.getCurrentPage() - 1) * pageParam.getPageSize());
        }
        return pageParam;
    }

}
