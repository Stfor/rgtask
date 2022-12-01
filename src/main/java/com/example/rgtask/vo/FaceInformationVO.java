package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FaceInformationVO", description = "FaceInformationVO")
public class FaceInformationVO implements Serializable {

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
     * 备注
     */
    private String remark;


}
