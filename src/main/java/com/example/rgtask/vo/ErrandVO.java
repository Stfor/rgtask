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
@ApiModel(value = "ErrandVO", description = "ErrandVO")
public class ErrandVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @NotBlank(groups = {Update.class}, message = "用户Id不能为空")
    @ApiModelProperty(value = "用户id", example = "1812312", required = true)
    private String id;

    /**
     * 标题
     */
    @NotBlank(groups = {Create.class},message = "标题不能为空")
    @ApiModelProperty(value = "标题", example = "1812312", required = true)
    private String title;

    /**
     * 内容
     */
    @NotBlank(groups = {Create.class},message = "内容不能为空")
    @ApiModelProperty(value = "内容", example = "1812312", required = true)
    private String content;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", example = "1812312", required = true)
    private String label;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期", example = "1812312", required = true)
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期", example = "1812312", required = true)
    private LocalDateTime endDate;

    /**
     * 取货地址
     */
    @ApiModelProperty(value = "取货地址", example = "1812312", required = true)
    private String taskAddress;

    /**
     * 送达地址
     */
    @ApiModelProperty(value = "送达地址", example = "1812312", required = true)
    private String deliveryAddress;


    /**
     * 酬劳
     */
    @ApiModelProperty(value = "酬劳", example = "1812312", required = true)
    private Double rewarded;


    /**
     * 非数据库字段
     * 图片base64集合
     */
    @ApiModelProperty(value = "图片集合", example = "")
    private List<String> pictures;
}
