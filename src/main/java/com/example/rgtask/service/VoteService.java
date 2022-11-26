package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.Vote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
public interface VoteService extends IService<Vote> {
    int insert(VoteVO voteVO);

    int updateAllById(VoteVO voteVO);

    boolean removeAllById(String id);

    IPage<VoteReturnVO> findPage(VotePageVO pageVO);

    List<MyVotedReturnVO> getVotedByUserId(String userId, String label);

    List<String> getLabel();
}
