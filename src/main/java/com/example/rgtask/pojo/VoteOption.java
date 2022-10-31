package com.example.rgtask.pojo;

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
 * @since 2022-10-31
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
    private String option;

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
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 注释
     */
    private String remark;


}
