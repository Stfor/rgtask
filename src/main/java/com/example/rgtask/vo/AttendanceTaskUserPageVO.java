package com.example.rgtask.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
public class AttendanceTaskUserPageVO extends PageVO implements Serializable {

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


}
