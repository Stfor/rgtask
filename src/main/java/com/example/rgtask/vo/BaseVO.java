package com.example.rgtask.vo;

import com.example.rgtask.validation.Create;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "BaseVO", description = "VO基础类")
public class BaseVO {

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "发起请求时间", required = true, example = "2018-09-29 11:26:20", dataType = "string")
//    private Date createTime;// 客户端访问时间
//    @ApiModelProperty(value = "请求者ID", hidden = false, example = "10001")
//    private String requesterId;// 请求者id

    @Length(max = 254, message = "备注字数最大为254", groups = {Create.class})
    @ApiModelProperty(value = "备注", example = "备注内容")
    protected String remarks;    // 备注

    public BaseVO() {
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "remarks='" + remarks + '\'' +
                '}';
    }
}
