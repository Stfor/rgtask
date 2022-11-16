package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.mapper.PicturesMapper;
import com.example.rgtask.mapper.VoteOptionMapper;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Vote;
import com.example.rgtask.mapper.VoteMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.pojo.VoteOption;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import com.example.rgtask.vo.VotePageVO;
import com.example.rgtask.vo.VoteReturnVO;
import com.example.rgtask.vo.VoteVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Slf4j
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
    private void setVoteOptionService(VoteOptionService voteOptionService){
        this.voteOptionService = voteOptionService;
    }
    @Autowired
    private void  setPicturesService(PicturesService picturesService){
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

    @Override
    public IPage<VoteReturnVO> findPage(VotePageVO pageVO) {
        Page<Vote> page = new Page<Vote>(pageVO.getPageNo(),pageVO.getPageSize());
        //创建查询条件
        QueryWrapper<Vote> wrapper = new QueryWrapper<>();

        IPage<Vote> iPage = voteMapper.selectPage(page,wrapper);
        IPage<VoteReturnVO> returnVOIPage = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        BeanUtils.copyProperties(iPage,returnVOIPage);

        List<Vote> records = iPage.getRecords();
        List<VoteReturnVO> voRecords = new ArrayList<>();
        //复制所有的vote
        for (Vote vote : records){
            VoteReturnVO returnVO = new VoteReturnVO();
            BeanUtils.copyProperties(vote,returnVO);
            voRecords.add(returnVO);
        }
        //为所有的vote添加选项以及图片
        for (VoteReturnVO vo : voRecords){
            vo.setVoteOptionVOList(voteOptionService.findVoteOptionByAreaId(vo.getId()));
            vo.setPictures(picturesService.findPictures(vo.getId()));
        }
        returnVOIPage.setRecords(voRecords);
        return returnVOIPage;
    }
}
