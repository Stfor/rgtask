package com.example.rgtask.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "IdleGoodsVO", description = "IdleGoodsVO")
public class IdleGoodsVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 闲置信息id
     */
    private String id;

    /**
     * 发布人id
     */
    private String sponsorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 标签（什么类型的物品）
     */
    private String label;

    /**
     * 闲置信息名称
     */
    private String goodsName;

    /**
     * 闲置信息描述
     */
    private String description;

    /**
     * 价格
     */
    private Double price;



    /**
     * 投票图片 非数据库字段
     */
    private List<String> pictures;
}
