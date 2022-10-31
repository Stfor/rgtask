package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "ErrandPageVO", description = "ErrandPageVO")
public class ErrandPageVO extends PageVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
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
     * 是否完成/支付（状态）
     */
    private String status;

    /**
     * 酬劳
     */
    private Double rewarded;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
