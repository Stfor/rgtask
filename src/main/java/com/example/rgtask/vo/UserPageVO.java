package com.example.rgtask.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserPageVO", description = "UserPageVO")
public class UserPageVO extends PageVO{

    /**
     * 登录名
     */
    private String loginName;

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
    private String credit;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 最近登录日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime loginDate;

    /**
     * 是否允许登录
     */
    private String loginFlag;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 备注信息
     */
    private String remarks;

}
