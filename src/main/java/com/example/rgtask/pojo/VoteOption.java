package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class VoteOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 投票id
     */
    private String voteId;

    /**
     * 选项
     */
    private String choice;

    /**
     * 描选项描述
     */
    private String description;

    /**
     * 图片
     */
    private String picture;

    /**
     * 赞同人数
     */
    private Integer agreeNum;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    /**
     * 注释
     */
    private String remark;


}
