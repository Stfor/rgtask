package com.example.rgtask.pojo;

import java.time.LocalDateTime;
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
 * @since 2022-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    private String id;

    /**
     * 分组创建人id
     */
    private String userId;

    /**
     * 分组标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 是否允许更改基本信息
     */
    private String status;

}
