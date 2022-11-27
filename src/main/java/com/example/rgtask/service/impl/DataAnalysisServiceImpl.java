package com.example.rgtask.service.impl;

import com.example.rgtask.mapper.VoteOptionMapper;
import com.example.rgtask.pojo.DataAnalysis;
import com.example.rgtask.mapper.DataAnalysisMapper;
import com.example.rgtask.service.DataAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.vo.AnalysisGetVO;
import com.example.rgtask.vo.AnalysisReturnVO;
import com.example.rgtask.vo.AnalysisVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-26
 */
@Service
public class DataAnalysisServiceImpl extends ServiceImpl<DataAnalysisMapper, DataAnalysis> implements DataAnalysisService {
    private DataAnalysisMapper dataAnalysisMapper;
    private VoteOptionMapper voteOptionMapper;
    @Autowired
    private void setDataAnalysisMapper(DataAnalysisMapper dataAnalysisMapper){
        this.dataAnalysisMapper = dataAnalysisMapper;
    }
    @Autowired
    private void setVoteOptionMapper(VoteOptionMapper voteOptionMapper){
        this.voteOptionMapper = voteOptionMapper;
    }

    @Override
    public List<AnalysisReturnVO> getAnalysis(AnalysisVO analysisVO) {
//        List<String> allChoice = voteOptionMapper.getAllChoiceByVoteId(analysisVO.getVoteId());
        //获取数据库基本信息
        List<AnalysisGetVO> getVOS = dataAnalysisMapper.getAnalysis(analysisVO.getCondition(),analysisVO.getVoteId());
        Map<String,AnalysisReturnVO> returnVOS = new HashMap<>();
        for (AnalysisGetVO getVO : getVOS){
            if (returnVOS.containsKey(getVO.getXAxis())){
                AnalysisReturnVO returnVO = returnVOS.get(getVO.getXAxis());
                //放置新的选项
                Map<String, Integer> choice = returnVO.getOption();
                choice.put(getVO.getChoice(),getVO.getYAxis());
                returnVO.setOption(choice);
                //改变总数量
                returnVO.setAllNum(returnVO.getAllNum()+getVO.getYAxis());
                //刷新
                returnVOS.replace(getVO.getXAxis(),returnVO);
            }else {
                AnalysisReturnVO returnVO = new AnalysisReturnVO();
                //放置新的选项
                Map<String, Integer> choice = new HashMap<>();
                choice.put(getVO.getChoice(),getVO.getYAxis());
                returnVO.setOption(choice);
                //改变总数量
                returnVO.setAllNum(getVO.getYAxis());
                //设置横坐标
                returnVO.setXAxis(getVO.getXAxis());
                returnVOS.put(getVO.getXAxis(),returnVO);
            }
        }
        List<AnalysisReturnVO> analysis = new ArrayList<>();
        for (String str : returnVOS.keySet()){
            if (StringUtils.isNotBlank(returnVOS.get(str).getXAxis())){
                analysis.add(returnVOS.get(str));
            }
        }
        return analysis;
    }
}
