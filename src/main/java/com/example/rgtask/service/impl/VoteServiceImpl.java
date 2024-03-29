package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Vote;
import com.example.rgtask.mapper.VoteMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.pojo.VoteLog;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.service.VoteLogService;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.MyVotedReturnVO;
import com.example.rgtask.vo.VotePageVO;
import com.example.rgtask.vo.VoteReturnVO;
import com.example.rgtask.vo.VoteVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private VoteLogService voteLogService;
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
    @Autowired
    private void  setVoteLogService(VoteLogService voteLogService){
        this.voteLogService = voteLogService;
    }

    @Override
    public int insert(VoteVO voteVO) {
        Vote vote = new Vote();
        BeanUtils.copyProperties(voteVO,vote);
        vote.setId(UUID.randomUUID().toString());
        vote.setCreateDate(LocalDateTime.now());
        vote.setUserId(UserUtils.getPrincipal());
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
        wrapper.orderByDesc("create_date");
        if (StringUtils.isNotBlank(pageVO.getLabel())){
            wrapper.eq("label",pageVO.getLabel());
        }
        if (StringUtils.isNotBlank(pageVO.getId())){
            wrapper.eq("id",pageVO.getId());
        }

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
            vo.setAvatar(UserUtils.getUserAvatarFromRedis(vo.getUserId()));
        }
        returnVOIPage.setRecords(voRecords);
        return returnVOIPage;
    }

    @Override
    public List<MyVotedReturnVO> getVotedByUserId(String userId, String label) {
        List<VoteLog> voteLogList = voteLogService.getVoteLogByUserId(userId);
        List<MyVotedReturnVO> myVotedReturnVOS = new ArrayList<>();
        String avatar = UserUtils.getUserAvatarFromRedis(userId);
        for (VoteLog voteLog : voteLogList){

            //投票选项数据
            Vote vote = voteMapper.selectById(voteLog.getVoteid());
            if (StringUtils.isNotBlank(label) && !vote.getLabel().equals(label)){
                continue;
            }
            MyVotedReturnVO vo = new MyVotedReturnVO();
            vo.setAvatar(avatar);
            BeanUtils.copyProperties(vote,vo);
            vo.setVoteId(vote.getId());
            //我投的选项
            vo.setChoice(voteLogService.getMyVotedChoice(userId,vo.getVoteId()));
            //获得当前投票的投票人数
            vo.setNum(voteLogService.getVoteNumByVoteId(vote.getId()));
            myVotedReturnVOS.add(vo);
        }
        return myVotedReturnVOS;
    }

    @Override
    public List<String> getLabel() {
        return voteMapper.getLabel();
    }
}
