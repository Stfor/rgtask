package com.example.rgtask.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.utils.UserUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * Created by maple on 2018-09-21.
 */
@ApiModel(value = "CommonResult", description = "返回结果常用类")
public class CommonResult extends BaseResult {

    @ApiModelProperty(value = "请求者id", example = "10001")
    private String requesterId;// 请求者id
    @ApiModelProperty(value = "具体返回的数据")
    private JSONObject item;// 具体返回的数据

    @Override
    public CommonResult init() {
        super.init();
        this.item = new JSONObject();
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.requesterId = user.getId();
        } else {
            this.requesterId = "未登录";
        }
        return this;
    }

    public CommonResult() {
    }

    public CommonResult(String requesterId) {
        this.requesterId = requesterId;
    }

    public CommonResult(String requesterId, JSONObject item) {
        this.requesterId = requesterId;
        this.item = item;
    }


    public CommonResult(int msgCode, String errMsg, LocalDateTime requestDateTime, LocalDateTime receiptDateTime, LocalDateTime returnDateTime) {
        super(msgCode, errMsg, requestDateTime, receiptDateTime, returnDateTime);
    }

    public CommonResult(int msgCode, String errMsg, LocalDateTime requestDateTime, LocalDateTime receiptDateTime, LocalDateTime returnDateTime, String requesterId, JSONObject item) {
        super(msgCode, errMsg, requestDateTime, receiptDateTime, returnDateTime);
        this.requesterId = requesterId;
        this.item = item;
    }


    public CommonResult success(String key, Object value) {
        super.success();
        if (null != key && null != value) {
            this.item.put(key, value);
        }
        return this;
    }

//    public CommonResult fail(int msgCode, String customErrMsg) {
//        super.fail(msgCode);
//        this.errMsg = this.errMsg+ "\n" + customErrMsg;
//        return this;
//    }

    public void putItem(String key, Object value) {
        this.item.put(key, value);
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public JSONObject getItem() {
        return item;
    }

    public void setItem(JSONObject item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "requesterId='" + requesterId + '\'' +
                ", item=" + item +
                '}';
    }
}
