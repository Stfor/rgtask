package com.example.rgtask.service;

import com.example.rgtask.pojo.DataAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.AnalysisGetVO;
import com.example.rgtask.vo.AnalysisReturnVO;
import com.example.rgtask.vo.AnalysisVO;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-26
 */
public interface DataAnalysisService extends IService<DataAnalysis> {
    List<AnalysisReturnVO> getAnalysis(AnalysisVO analysisVO);
}
