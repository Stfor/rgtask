package com.example.rgtask.mapper;

import com.example.rgtask.pojo.VoteOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rgtask.vo.VoteOptionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xa
 * @since 2022-11-11
 */
@Mapper
public interface VoteOptionMapper extends BaseMapper<VoteOption> {
    List<String> getAllChoiceByVoteId(@Param("voteId") String voteId);
}
