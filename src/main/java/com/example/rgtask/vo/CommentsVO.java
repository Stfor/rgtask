package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CommentsVO", description = "CommentsVO")
public class CommentsVO {
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String id;

    /**
     * 区域id（悬赏，投票等id共用）
     */
    private String areaId;

    /**
     * 评论者id
     */
    private String userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父级评论id
     */
    private String parentId;

    /**
     * 点赞数量
     */
    private Integer thumbsUp;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 注释
     */
    private String remark;

}
