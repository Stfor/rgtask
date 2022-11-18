package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.rgtask.pojo.VoteLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
public class VoteLogVO implements Serializable {

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

    VoteLogVO(){

    }

    public VoteLogVO(String voteid, String userid) {
        this.voteid = voteid;
        this.userid = userid;
    }
}
