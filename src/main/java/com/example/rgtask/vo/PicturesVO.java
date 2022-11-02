package com.example.rgtask.vo;

import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xa
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PicturesVO", description = "PartTimeJobVO")
public class PicturesVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 照片id
     */
    @NotBlank(groups = {Update.class}, message = "照片id不能为空")
    @ApiModelProperty(value = "照片id", example = "1812312", required = true)
    private String id;

    /**
     * 区域id（多个位置共享）
     */
    @NotBlank(groups = {Update.class, Create.class}, message = "区域id不能为空")
    @ApiModelProperty(value = "区域id", example = "1812312", required = true)
    private String areaId;

    /**
     * 图片信息(采用base64编码)
     */
    @NotBlank(groups = {Update.class, Create.class}, message = "图片信息不能为空")
    @ApiModelProperty(value = "图片信息", example = "1812312", required = true)
    private String picture;



}
