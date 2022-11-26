package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VoteLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 投票id
     */
    @TableField("voteId")
    private String voteid;

    /**
     * 投票人
     */
    @TableField("userId")
    private String userid;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 创建时间
     */
    private LocalDateTime updateDate;

    /**
     * 备注
     */
    private String remark;


    /**
     * 我投的选项
     */
    @TableField("choice")
    private String choice;

}
