package com.example.rgtask.service;

import com.example.rgtask.pojo.VoteLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.VoteLogVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
public interface VoteLogService extends IService<VoteLog> {
    Boolean insert(VoteLogVO voteLogVO);

    List<VoteLog> getVoteLogByUserId(String userId);

    Boolean hadVoted(String userId,String voteId);

    String getMyVotedChoice(String userId,String voteId);

    Integer getVoteNumByVoteId(String voteId);
}
