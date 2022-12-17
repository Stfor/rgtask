package com.example.rgtask.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MessageVO2", description = "MessageVO2")
public class MessageVO2 {
    private String id;

    private String msgContent;

    private String msgType;
}
