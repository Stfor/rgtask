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
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comments implements Serializable {

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
