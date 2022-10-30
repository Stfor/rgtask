package com.example.rgtask.pojo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.utils.UserUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * Created by maple on 2018-09-30.
 */
@ApiModel(value = "PageResult", description = "返回页面列表结果基础类")
public class PageResult<T> extends BaseResult {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "请求者id", example = "10001")
    private String requesterId;// 请求者id
    @ApiModelProperty(value = "页面数据")
    private Page<T> page;// 页面数据

    //    @Override
//    public PageResult<T> init() {
//        return (PageResult<T>) super.init();
//    }
    @Override
    public PageResult<T> init() {
        super.init();
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.requesterId = user.getId();
        } else {
            this.requesterId = "未登录";
        }
        return this;
    }

    public PageResult() {
        this.setMsgCode(0);
    }

    public PageResult(String requesterId, Page<T> page) {
        this.requesterId = requesterId;
        this.page = page;
    }

    public PageResult(int msgCode, String errMsg, LocalDateTime requestDateTime, LocalDateTime receiptDateTime, LocalDateTime returnDateTime, String requesterId, Page<T> page) {
        super(msgCode, errMsg, requestDateTime, receiptDateTime, returnDateTime);
        this.requesterId = requesterId;
        this.page = page;
    }


    public PageResult<T> success(Page<T> page) {
        super.success();
        this.setPage(page);
        return this;
    }

    //    public PageResult<T> fail(int msgCode, String key, Object value) {
//        super.fail(msgCode);
//        if (null != key && null != value) {
//            this.item.put(key, value);
//        }
//        return this;
//    }
    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "requesterId='" + requesterId + '\'' +
                ", page=" + page +
                '}';
    }
}
