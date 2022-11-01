package com.example.rgtask.pojo;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
 * @since 2022-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别（男0女1）
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 经验值（成就）
     */
    private String experienceValue;

    /**
     * 用户信用
     */
    private Integer credit;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 最近登录日期
     */
    private LocalTime loginDate;

    /**
     * 是否允许登录
     */
    private String loginFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 随机盐
     */
    private String salt;
}
