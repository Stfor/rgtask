package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FaceInformationPageVO", description = "FaceInformationPageVO")
public class FaceInformationPageVO extends PageVO{
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




}
