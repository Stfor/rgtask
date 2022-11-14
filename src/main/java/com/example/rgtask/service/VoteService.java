package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Vote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.pojo.VoteOption;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.VotePageVO;
import com.example.rgtask.vo.VoteReturnVO;
import com.example.rgtask.vo.VoteVO;

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
}
