package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MessageVO", description = "MessageVO")
public class MessageVO extends BaseVO implements Serializable {
    private String sendId;

    private String receiveId;

    private String msgContent;

}
