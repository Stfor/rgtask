package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Errand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private String id;

    /**
     * 发起人id
     */
    private String sponsorId;

    /**
     * 接受人id
     */
    private String recipientId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private String label;

    /**
     * 开始日期
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    /**
     * 取货地址
     */
    private String taskAddress;

    /**
     * 送达地址
     */
    private String deliveryAddress;

    /**
     * 是否完成/支付（状态）0:未结单 1:已接单 2：待支付 3:已完成
     */
    private String status;

    /**
     * 酬劳
     */
    private Double rewarded;

    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 电话号码
     */
    private String sponsorPhone;
}
