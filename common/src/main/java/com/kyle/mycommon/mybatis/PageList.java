package com.kyle.mycommon.mybatis;

/**
 * 分页列表对象
 * @author yangkaile
 * @date 2018-11-28 09:52:26
 */
public class PageList {
    private int total;
    private int pageSize = 20;
    private int currentPage = 1;
    private int startRows ;
    private Object data;

    public PageList() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStartRows() {
        return startRows;
    }

    public void setStartRows(int startRows) {
        this.startRows = startRows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.startRows = this.pageSize * (this.currentPage - 1);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.startRows = this.pageSize * (this.currentPage - 1);
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", startRows=" + startRows +
                ", data=" + data +
                '}';
    }
}
