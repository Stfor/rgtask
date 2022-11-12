package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.rgtask.vo.VoteOptionVO;
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
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投票id
     */
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
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注信息
     */
    private String remark;

//    /**
//     * 投票选项 非数据库字段
//     */
//    private List<VoteOptionVO> voteOptionVOList;
//
//    /**
//     * 投票图片 非数据库字段
//     */
//    private List<String> pictures;

}
