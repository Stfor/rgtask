package com.example.rgtask.vo;

import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
@ApiModel(value = "UserVO", description = "UserVO")
public class UserVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @NotBlank(groups = {Update.class}, message = "用户Id不能为空")
    @ApiModelProperty(value = "用户id", example = "1812312", required = true)
    private String id;

    /**
     * 登录名
     */
    @NotBlank(groups = {Create.class}, message = "用户登录名不能为空")
    @ApiModelProperty(value = "用户登录名", example = "root", required = true)
    private String loginName;

    /**
     * 登录密码
     */
    @NotBlank(groups = {Create.class}, message = "用户密码")
    @ApiModelProperty(value = "用户密码", example = "123456", required = true)
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "用户名", example = "张三")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "用户年龄", example = "12")
    private Integer age;

    /**
     * 地址
     */
    @ApiModelProperty(value = "用户地址", example = "福建省福州市....")
    private String address;

    /**
     * 性别（男0女1）
     */
    @ApiModelProperty(value = "用户性别", example = "男")
    private String sex;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "用户邮箱", example = "123456@qq.com")
    private String email;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "用户电话", example = "123456")
    private String phone;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", example = "注意使用base64上传")
    private String photo;

}
