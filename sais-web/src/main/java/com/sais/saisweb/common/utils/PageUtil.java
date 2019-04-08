package com.sais.saisweb.common.utils;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 分页工具类
 */
public class PageUtil {
    /**
     *
     * @param page PageInfo包装对象
     * @param result 返回界面参数
     * @return 包含界面参数的Map集合
     */
    public static Map<String,Object> setPageInfo(PageInfo page, Map<String, Object> result) {
        result.put("pageNum", page.getPageNum());//当前页数
        result.put("firstPage", page.getNavigateFirstPage());//第一页
        result.put("lastPage", page.getNavigateLastPage());//最后一页
        result.put("pages", page.getPages());//总页数
        result.put("total", page.getTotal());//记录总数
        return result;
    }
}
