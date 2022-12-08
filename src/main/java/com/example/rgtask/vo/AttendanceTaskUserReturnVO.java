package com.example.rgtask.vo;

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
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttendanceTaskUserReturnVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 点名任务id
     */
    private String taskId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 是否签到
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 非数据库字段 用户真实姓名
     */
    private String name;


}
