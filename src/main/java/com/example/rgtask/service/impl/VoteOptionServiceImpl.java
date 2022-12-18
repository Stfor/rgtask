package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.VoteOption;
import com.example.rgtask.mapper.VoteOptionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.vo.VoteOptionVO;
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
 * @since 2022-11-11
 */
@Service
public class VoteOptionServiceImpl extends ServiceImpl<VoteOptionMapper, VoteOption> implements VoteOptionService {
    private VoteOptionMapper voteOptionMapper;
    @Autowired
    private void setVoteOptionMapper(VoteOptionMapper voteOptionMapper){
        this.voteOptionMapper = voteOptionMapper;
    }
    @Override
    public boolean insertList(List<VoteOptionVO> voteOptionVOList, String areaId) {
        if (voteOptionVOList != null){
            for (VoteOptionVO voteOptionVO : voteOptionVOList){
                VoteOption voteOption = new VoteOption();
                BeanUtils.copyProperties(voteOptionVO,voteOption);
                voteOption.setVoteId(areaId);
                voteOption.setCreateDate(LocalDateTime.now());
                if (voteOptionMapper.insert(voteOption) <= 0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeByVoteId(String voteId) {
        QueryWrapper<VoteOption> wrapper = new QueryWrapper<>();
        wrapper.eq("vote_id",voteId);
        if(voteOptionMapper.delete(wrapper) > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<VoteOption> findVoteOptionByAreaId(String areaId) {
        QueryWrapper<VoteOption> wrapper = new QueryWrapper<>();
        wrapper.eq("vote_id",areaId);
        return voteOptionMapper.selectList(wrapper);
    }

    @Override
    public Boolean agree(String voteOptionId) {
        return true;
    }


}
