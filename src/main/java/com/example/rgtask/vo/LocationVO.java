package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LocationVO", description = "LocationVO")
public class LocationVO {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    @TableField("userId")
    private String userid;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 是否允许修改等
     */
    private String status;
}
