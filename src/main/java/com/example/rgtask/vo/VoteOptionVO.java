package com.example.rgtask.vo;

import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VoteOptionVO", description = "VoteOptionVO")
public class VoteOptionVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotBlank(groups = {Update.class}, message = "选项Id不能为空")
    @ApiModelProperty(value = "选项id", example = "1812312", required = true)
    private String id;

    /**
     * 投票id
     */
    @NotBlank(groups = {Create.class}, message = "投票Id不能为空")
    @ApiModelProperty(value = "投票id", example = "1812312", required = true)
    private String voteId;

    /**
     * 选项
     */
    @ApiModelProperty(value = "选项", example = "A", required = true)
    private String choice;

    /**
     * 描选项描述
     */
    @ApiModelProperty(value = "描选项描述", example = "好", required = true)
    private String description;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片", example = "181231asdsad2", required = true)
    private String picture;

    /**
     * 赞同人数
     */
    @ApiModelProperty(value = "赞同人数", example = "12", required = true)
    private Integer agreeNum;


}
