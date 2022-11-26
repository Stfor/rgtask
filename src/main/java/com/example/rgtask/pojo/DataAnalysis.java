package com.example.rgtask.pojo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author xa
 * @since 2022-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 投票id
     */
    private String voteId;

    /**
     * 省份
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 年级
     */
    private String grade;

    /**
     * 性别（男0女1）
     */
    private String sex;

    private String university;

    /**
     * 我投的选项
     */
    private String choice;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private Date createDate;


}
