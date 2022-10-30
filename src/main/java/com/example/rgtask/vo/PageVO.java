package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

@ApiModel(value = "PageVO", description = "VO列表基础类")
public class PageVO extends BaseVO {

    @Min(value = 1, message = "当前页码必须为整数或者大于等于1")
    @ApiModelProperty(value = "当前页码", name = "pageNo", example = "1")
    private int pageNo = 1; // 当前页码
    @Min(value = 1, message = "每页数量必须为整数或者大于等于1")
    @ApiModelProperty(value = "每页数量", name = "pageSize", example = "20")
    private int pageSize = 30;// 每页数量
    @ApiModelProperty(value = "排序方式", name = "orderBy", example = " updatedate desc, name asc")
    private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc


    public PageVO() {
    }

    public PageVO(int pageNo, int pageSize, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", remarks='" + remarks + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
