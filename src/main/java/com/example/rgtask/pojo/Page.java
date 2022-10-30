package com.example.rgtask.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Page", description = "页面列表结果基础类")
public class Page<T> implements Serializable {
    @ApiModelProperty(value = "当前页码", example = "1")
    private int pageNo = 1; // 当前页码
    @ApiModelProperty(value = "页面大小", example = "30")
    private int pageSize = 30; // 页面大小，设置为“-1”表示不进行分页（分页无效）
    @ApiModelProperty(value = "总记录数", example = "500")
    private long count;// 总记录数，设置为“-1”表示不查询总数
    @ApiModelProperty(value = "首页索引", example = "1")
    private int first;// 首页索引
    @ApiModelProperty(value = "尾页索引", example = "10")
    private int last;// 尾页索引
    @ApiModelProperty(value = "上一页索引", example = "1")
    private int prev;// 上一页索引
    @ApiModelProperty(value = "下一页索引", example = "3")
    private int next;// 下一页索引
    @ApiModelProperty(value = "是否是第一页", example = "true")
    private boolean firstPage;//是否是第一页
    @ApiModelProperty(value = "是否是最后一页", example = "true")
    private boolean lastPage;//是否是最后一页
    @ApiModelProperty(value = "显示页面长度", example = "8")
    private int length = 8;// 显示页面长度
    @ApiModelProperty(value = "前后显示页面长度", example = "1")
    private int slider = 1;// 前后显示页面长度
    @ApiModelProperty(value = "列表数据")
    private List<T> list = new ArrayList<T>();
    @ApiModelProperty(value = "标准查询有效", example = "updatedate desc")
    private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

//    private int firstResult;
//    private int maxResults;

    public Page() {
    }

    public Page(int pageNo, int pageSize, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public Page(int pageNo, int pageSize, long count, int first, int last, int prev, int next, boolean firstPage, boolean lastPage, int length, int slider, List<T> list, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.count = count;
        this.first = first;
        this.last = last;
        this.prev = prev;
        this.next = next;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.length = length;
        this.slider = slider;
        this.list = list;
        this.orderBy = orderBy;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSlider() {
        return slider;
    }

    public void setSlider(int slider) {
        this.slider = slider;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    //TODO 页码大于总数，将会返回全部数据，后期可修改为空的List
    public int getFirstResult() {
        int firstResult = (getPageNo() - 1) * getPageSize();
        if (firstResult >= getCount()) {
            firstResult = 0;
        }
        return firstResult;
    }

    public int getMaxResults() {
        return getPageSize();
    }
}
