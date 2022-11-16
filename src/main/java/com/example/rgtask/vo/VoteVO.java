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
@ApiModel(value = "VoteVO", description = "VoteVO")
public class VoteVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投票id
     */
    @NotBlank(groups = {Update.class}, message = "投票Id不能为空")
    @ApiModelProperty(value = "投票id", example = "1812312", required = true)
    private String id;

//    /**
//     * 用户id
//     */
//    @NotBlank(groups = {Create.class}, message = "用户Id不能为空")
//    @ApiModelProperty(value = "用户id", example = "1812312", required = true)
//    private String userId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", example = "衣服", required = true)
    private String title;

    /**
     * 内容描述
     */
    @ApiModelProperty(value = "内容描述", example = "哪个好", required = true)
    private String content;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", example = "情感", required = true)
    private String label;

    /**
     * 投票选项 非数据库字段
     */
    private List<VoteOptionVO> voteOptionVOList;

    /**
     * 投票图片 非数据库字段
     */
    private List<String> pictures;
}
