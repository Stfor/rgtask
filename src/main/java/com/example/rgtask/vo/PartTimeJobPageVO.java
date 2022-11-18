package com.example.rgtask.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "PartTimeJobPageVO", description = "PartTimeJobPageVO")
public class PartTimeJobPageVO extends PageVO implements Serializable {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    /**
     * 兼职结束日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private String delFlag;


}
