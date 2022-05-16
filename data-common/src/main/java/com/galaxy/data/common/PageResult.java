package com.galaxy.data.common;

/**
 * 公共返回类 - 分页
 *
 * @author yao.qian
 */
public class PageResult<T> extends Result<T> {

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer currentPage;

    public PageResult() {
    }

    public PageResult(Integer total, Integer currentPage) {
        this.total = total;
        this.currentPage = currentPage;
    }

    public PageResult(Integer total, Integer currentPage, Integer code, String message) {
        this.total = total;
        this.currentPage = currentPage;
        this.code = code;
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public PageResult<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public PageResult<T> setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }
}
