package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Pictures implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 照片id
     */
    private String id;

    /**
     * 区域id（多个位置共享）
     */
    private String areaId;

    /**
     * 图片信息(采用base64编码)
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
     * 注释
     */
    private String remark;
    /**
     * 是否完成/支付（状态）
     */
    private String status;

}
