package com.example.rgtask.vo;

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
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttendanceTaskUserVO implements Serializable {

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
     * 备注
     */
    private String remarks;

    AttendanceTaskUserVO(){}

    public AttendanceTaskUserVO(String taskId, String userId, String remarks){
        this.remarks = remarks;
        this.userId = userId;
        this.taskId = taskId;
    }

}
