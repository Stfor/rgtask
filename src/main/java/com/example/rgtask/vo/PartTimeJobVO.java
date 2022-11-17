package com.example.rgtask.vo;

import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "PartTimeJobVO", description = "PartTimeJobVO")
public class PartTimeJobVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 兼职任务id
     */
    @NotBlank(groups = {Update.class}, message = "兼职任务id不能为空")
    @ApiModelProperty(value = "兼职任务id", example = "asdasdasd", required = true)
    private String id;

    /**
     * 兼职标题
     */
    @NotBlank(groups = {Create.class}, message = "兼职标题不能为空")
    @ApiModelProperty(value = "兼职标题", example = "asdasda", required = true)
    private String title;

    /**
     * 兼职内容
     */
    @NotBlank(groups = {Create.class}, message = "兼职内容不能为空")
    @ApiModelProperty(value = "兼职内容", example = "asdasdasd", required = true)
    private String content;

    /**
     * 兼职人数
     */
    @ApiModelProperty(value = "兼职人数", example = "1812312", required = true)
    private Integer recipientNum;

    /**
     * 兼职开始日期
     */
    @ApiModelProperty(value = "兼职开始日期", example = "2018-09-29 11:26", required = true)
    private LocalDateTime startTime;

    /**
     * 兼职结束日期
     */
    @ApiModelProperty(value = "兼职结束日期", example = "2018-09-29 11:26", required = true)
    private LocalDateTime endTime;

    /**
     * 工作地点
     */
    @ApiModelProperty(value = "工作地点", example = "asdasdasd", required = true)
    private String workPlace;

    /**
     * 工作要求
     */
    @ApiModelProperty(value = "工作要求", example = "asdasd", required = true)
    private String jobRequirements;

    /**
     * 酬劳
     */
    @ApiModelProperty(value = "酬劳", example = "100", required = true)
    private Double rewarded;
}
