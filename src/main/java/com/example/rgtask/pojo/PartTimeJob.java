package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class PartTimeJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 兼职任务id
     */
    private String id;

    /**
     * 发起人id
     */
    private String sponsorId;

    /**
     * 兼职标题
     */
    private String title;

    /**
     * 兼职内容
     */
    private String content;

    /**
     * 兼职人数
     */
    private Integer recipientNum;

    /**
     * 兼职开始日期
     */
    private LocalDateTime startTime;

    /**
     * 兼职结束日期
     */
    private LocalDateTime endTime;

    /**
     * 工作地点
     */
    private String workPlace;

    /**
     * 工作要求
     */
    private String jobRequirements;

    /**
     * 酬劳
     */
    private Double rewarded;

    /**
     * 备注信息
     */
    private String remark;

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


}
