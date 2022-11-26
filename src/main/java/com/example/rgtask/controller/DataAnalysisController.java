package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.DataAnalysisService;
import com.example.rgtask.vo.AnalysisGetVO;
import com.example.rgtask.vo.AnalysisReturnVO;
import com.example.rgtask.vo.AnalysisVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-26
 */
@RestController
@RequestMapping("/data-analysis")
@Api(value = "DataAnalysisController", tags = "数据分析接口")
public class DataAnalysisController {
    private DataAnalysisService dataAnalysisService;
    @Autowired
    private void setDataAnalysisService(DataAnalysisService dataAnalysisService){
        this.dataAnalysisService = dataAnalysisService;
    }

    @PostMapping("/analysis")
    public CommonResult analysis(@RequestBody AnalysisVO analysisVO){
        CommonResult result = new CommonResult().init();
        List<AnalysisReturnVO> vos = dataAnalysisService.getAnalysis(analysisVO);
        return result.success("analysis",vos);
    }
}
