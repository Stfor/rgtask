package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FaceInformationReturnVO", description = "FaceInformationReturnVO")
public class FaceInformationReturnVO {
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
     * 图片地址
     */
    private String picture;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
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

}
