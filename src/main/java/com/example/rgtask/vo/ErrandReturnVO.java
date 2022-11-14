package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class ErrandReturnVO implements Serializable {

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
     * 是否完成/支付（状态）0:未结单 1:已接单 2：待支付 3:已完成
     */
    private String status;

    /**
     * 酬劳
     */
    private Double rewarded;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图片base64集
     */
    private List<String> pictures;

    /**
     * 用户电话
     */
    private String sponsorPhone;
}
