package com.example.rgtask.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AnalysisReturnVO {
    String xAxis;

    Map<String,Integer> option;

    Integer allNum;
}
