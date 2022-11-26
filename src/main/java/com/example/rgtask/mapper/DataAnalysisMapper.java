package com.example.rgtask.mapper;

import com.example.rgtask.pojo.DataAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rgtask.vo.AnalysisGetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author xa
 * @since 2022-11-26
 */
@Mapper
public interface DataAnalysisMapper extends BaseMapper<DataAnalysis> {
    List<AnalysisGetVO> getAnalysis(@Param("condition") String condition, @Param("voteId") String voteId);

}
