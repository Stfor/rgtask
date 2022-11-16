package com.example.rgtask.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UniversityVO", description = "UniversityVO")
public class UniversityVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String schoolName;

    private String schoolCode;

    private String competentDarptment;

    private String location;

    private String level;

    private String type;

}
