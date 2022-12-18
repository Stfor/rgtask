package com.example.rgtask.service;

import com.example.rgtask.pojo.VoteOption;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.VoteOptionVO;
import com.example.rgtask.vo.VoteVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-11
 */
public interface VoteOptionService extends IService<VoteOption> {
    boolean insertList(List<VoteOptionVO> voteOptionVOList,String areaId);

    boolean removeByVoteId(String voteId);

    List<VoteOption> findVoteOptionByAreaId(String areaId);

    Boolean agree(String voteOptionId);

}
