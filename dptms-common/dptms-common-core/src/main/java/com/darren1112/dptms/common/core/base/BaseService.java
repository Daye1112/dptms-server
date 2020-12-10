package com.darren1112.dptms.common.core.base;


import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;

import java.util.List;

/**
 * 基础service类，包含service通用方法
 *
 * @author luyuhao
 * @since 20/01/13 23:58
 */
public class BaseService {

    /**
     * 创建分页对象
     * <p>
     * 例：
     * List<ActivityEntity> resultList = activityDao.listPage(stuId, pageParam);
     * Integer total = activityDao.countListPage(stuId);
     * return createPageBean(pageParam, total, resultList);
     *
     * @param pageParam  分页参数
     * @param total      总记录数
     * @param resultList 结果
     * @return 返回分页对象
     */
    protected <T> PageBean<T> createPageBean(PageParam pageParam, Long total, List<T> resultList) {
        PageBean<T> pageBean = new PageBean<>();
        if (pageParam.getPageSize() != null && pageParam.getCurrentPage() != null) {
            pageBean
                    //设置总记录数
                    .setTotal(total)
                    //设置content
                    .setContent(resultList)
                    //设置当前页
                    .setCurrentPage(pageParam.getCurrentPage())
                    //设置页大小
                    .setPageSize(pageParam.getPageSize())
                    //设置总页数 {(总记录数 + 页大小 - 1) / 页大小}
                    .setTotalPage((int) ((total + pageBean.getPageSize() - 1) / pageBean.getPageSize()))
                    //设置是否有后一页
                    .setHasNext(pageBean.getCurrentPage() < pageBean.getTotalPage())
                    //设置是否有前一页
                    .setHasPre(pageBean.getCurrentPage() > 1);
        }
        return pageBean;
    }
}
