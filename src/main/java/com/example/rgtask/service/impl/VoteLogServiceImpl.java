package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.VoteLog;
import com.example.rgtask.mapper.VoteLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.VoteLogService;
import com.example.rgtask.vo.VoteLogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@Service
public class VoteLogServiceImpl extends ServiceImpl<VoteLogMapper, VoteLog> implements VoteLogService {
    private VoteLogMapper voteLogMapper;
    @Autowired
    private void setVoteLogMapper(VoteLogMapper voteLogMapper){
        this.voteLogMapper = voteLogMapper;
    }

    @Override
    public Boolean insert(VoteLogVO voteLogVO) {
        VoteLog voteLog = new VoteLog();
        BeanUtils.copyProperties(voteLogVO,voteLog);
        voteLog.setCreateDate(LocalDateTime.now());
        if (voteLogMapper.insert(voteLog) > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<VoteLog> getVoteLogByUserId(String userId) {
        QueryWrapper<VoteLog> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        return voteLogMapper.selectList(wrapper);
    }

    @Override
    public Boolean hadVoted(String userId,String voteId) {
        QueryWrapper<VoteLog> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        wrapper.eq("voteId",voteId);
        if (voteLogMapper.selectCount(wrapper) != 0){
            return true;
        }else {
            return false;
        }
    }
}
