package com.example.rgtask.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Message",description = "私聊消息实体类")
public class Message {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(hidden = true)
    private String chatId;

    private String msgContent;

    private String sendId;

    private String receiveId;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    private String msgType;
}
