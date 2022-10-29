package com.example.rgtask.pojo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.utils.LocalDateUtils;
import com.example.rgtask.utils.MsgCodeUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by maple on 2018-09-21.
 */
@ApiModel(value = "BaseResult", description = "返回结果基础类")
public class BaseResult implements Serializable {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 1L;

    //    public HttpServletRequest request = SpringContextHolder.getBean(HttpServletRequest.class);
//    public HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    @ApiModelProperty(value = "请求返回状态码", required = true, example = "-10000")
    private int msgCode = 0;// 请求返回状态码

    @ApiModelProperty(value = "状态信息", required = true, example = "未知错误.")
    protected String errMsg;// 状态信息

    // 接收数据转化，将符合模式的字符串转化为Date
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // Date转json的时候采用配置的模式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "客户端请求服务器端时间", required = true, example = "2018-09-29 11:26:20")
    private LocalDateTime requestDateTime;// 客户端请求服务器端时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "服务器端接收请求时间", required = true, example = "2018-09-29 11:26:20")
    private LocalDateTime receiptDateTime;// 服务器端接收请求时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "服务端处理完请求时间", required = true, example = "2018-09-29 11:26:20")
    private LocalDateTime returnDateTime;// 服务端处理完请求时间

    public boolean isSuccess() {
        return this.msgCode >= 0;
    }

    public BaseResult init() {
        this.receiptDateTime = LocalDateTime.now();
        return this;
    }

    public BaseResult success() {
        int msgCode = 0;
        this.msgCode = msgCode;
        this.errMsg = MsgCodeUtils.getErrMsg(msgCode);
        return this;
    }


    public BaseResult fail(int msgCode) {
        this.msgCode = msgCode;
        this.errMsg = MsgCodeUtils.getErrMsg(msgCode);
        logger.error("接口报错. 错误码：{} 错误信息：{}", this.msgCode, this.errMsg);
        return this;
    }

    /**
     * 自定义错误信息
     *
     * @param msgCode 错误码
     * @param errMsg  错误信息
     * @return
     */
    public BaseResult failCustom(int msgCode, String errMsg) {
        this.msgCode = msgCode;
        // this.errMsg = MsgCodeUtils.getErrMsg(msgCode) + errMsg;
        if (StringUtils.isBlank(errMsg)) {
            this.errMsg = MsgCodeUtils.getErrMsg(msgCode);
        } else {
            String msgCodeMsg = MsgCodeUtils.getErrMsg(msgCode);
            if (StringUtils.isNotBlank(msgCodeMsg)) {
                this.errMsg = MsgCodeUtils.getErrMsg(msgCode) + errMsg;
            } else {
                this.errMsg = errMsg;
            }
        }
        logger.error("接口报错. 错误码：{} 错误信息：{}", this.msgCode, this.errMsg);
        return this;
    }

    /**
     * 自定义错误信息 默认错误码 -10000
     *
     * @param errMsg 错误信息
     * @return
     */
    public BaseResult failCustom(String errMsg) {
        this.msgCode = MsgCodeUtils.MSG_CODE_UNKNOWN;
        this.errMsg = errMsg;
        logger.error("接口报错. 错误码：{} 错误信息：{}", this.msgCode, this.errMsg);
        return this;
    }

    public BaseResult failIllegalArgument(List<FieldError> fieldErrors) {
        this.fail(MsgCodeUtils.MSG_CODE_ILLEGAL_ARGUMENT);
        StringBuilder errorStringBuilder = new StringBuilder(this.errMsg).append("\n");
        for (FieldError fieldError : fieldErrors) {
            logger.error(fieldError.toString());
            errorStringBuilder.append(fieldError.getDefaultMessage()).append("\n");
        }
        this.errMsg = errorStringBuilder.toString();
        return this;
    }

    public BaseResult end() {
        this.returnDateTime = LocalDateTime.now();
        return this;
    }

    public BaseResult() {
    }

    public BaseResult(int msgCode, String errMsg, LocalDateTime requestDateTime, LocalDateTime receiptDateTime, LocalDateTime returnDateTime) {
        this.msgCode = msgCode;
        this.errMsg = errMsg;
        this.requestDateTime = requestDateTime;
        this.receiptDateTime = receiptDateTime;
        this.returnDateTime = returnDateTime;
    }


    public void error(int msgCode) {
        this.msgCode = msgCode;
        this.errMsg = MsgCodeUtils.getErrMsg(msgCode);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public LocalDateTime getReceiptDateTime() {
        return receiptDateTime;
    }

    public void setReceiptDateTime(LocalDateTime receiptDateTime) {
        this.receiptDateTime = receiptDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "msgCode=" + msgCode +
                ", errMsg='" + errMsg + '\'' +
                ", requestDateTime=" + requestDateTime +
                ", receiptDateTime=" + receiptDateTime +
                ", returnDateTime=" + returnDateTime +
                '}';
    }


}
