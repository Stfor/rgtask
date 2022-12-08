package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "GroupVO", description = "GroupVO")
public class GroupVO implements Serializable {

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
     * 备注
     */
    private String remark;

    /**
     * 状态 是否允许更改基本信息
     */
    private String status;
}
