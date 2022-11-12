package com.example.rgtask.service.impl;

import com.example.rgtask.mapper.PicturesMapper;
import com.example.rgtask.mapper.VoteOptionMapper;
import com.example.rgtask.pojo.Vote;
import com.example.rgtask.mapper.VoteMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.pojo.VoteOption;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import com.example.rgtask.vo.VoteVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements VoteService {
    private VoteMapper voteMapper;
    private VoteOptionService voteOptionService;
    private PicturesService picturesService;
    @Autowired
    private void setVoteMapper(VoteMapper voteMapper){
        this.voteMapper = voteMapper;
    }
    @Autowired
    private void setVoteOptionMapper(VoteOptionService voteOptionMapper){
        this.voteOptionService = voteOptionMapper;
    }
    @Autowired void  setPicturesMapper(PicturesService picturesService){
        this.picturesService = picturesService;
    }

    @Override
    public int insert(VoteVO voteVO) {
        Vote vote = new Vote();
        BeanUtils.copyProperties(voteVO,vote);
        vote.setId(UUID.randomUUID().toString());
        vote.setCreateDate(LocalDateTime.now());
        if (voteMapper.insert(vote) > 0){
            if (voteOptionService.insertList(voteVO.getVoteOptionVOList(),vote.getId())
                    && picturesService.insertList(voteVO.getPictures(),vote.getId())){
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }

    @Override
    public int updateAllById(VoteVO voteVO) {
        return 0;
    }

    @Override
    public boolean removeAllById(String id) {
        if (voteMapper.deleteById(id) > 0){
            if (voteOptionService.removeByVoteId(id) && (picturesService.removeByAreaId(id) > 0) ){
                return true;
            }
            return false;
        }
        return false;
    }
}
