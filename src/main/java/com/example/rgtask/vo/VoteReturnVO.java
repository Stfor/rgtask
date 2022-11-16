package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.pojo.VoteOption;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "VoteReturnVO", description = "VoteReturnVO")
public class VoteReturnVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    /**
     * 用户id
     */
    private String userId;

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
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;


    /**
     * 备注信息
     */
    private String remark;

    /**
     * 投票选项 非数据库字段
     */
    private List<VoteOption> voteOptionVOList;

    /**
     * 投票图片 非数据库字段
     */
    private List<String> pictures;

}
