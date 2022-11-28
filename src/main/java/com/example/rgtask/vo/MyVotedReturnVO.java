package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value = "VoteReturnVO", description = "VoteReturnVO")
public class MyVotedReturnVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String voteId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容描述
     */
    private String content;

    /**
     * 标签
     */
    private String label;

    /**
     * 标参与人数非数据库字段
     */
    private Integer num;

    /**
     * 选项非当前数据库字段
     */
    private String choice;

    /**
     * 用户头像
     */
    private String avatar;
}
