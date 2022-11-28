package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.PartTimeJob;
import com.example.rgtask.mapper.PartTimeJobMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.PartTimeJobService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.PartTimeJobPageVO;
import com.example.rgtask.vo.PartTimeJobVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PartTimeJobServiceImpl extends ServiceImpl<PartTimeJobMapper, PartTimeJob> implements PartTimeJobService {
    private PartTimeJobMapper partTimeJobMapper;
    @Autowired
    private void setPartTimeJobMapper(PartTimeJobMapper partTimeJobMapper){
        this.partTimeJobMapper = partTimeJobMapper;
    }

    @Override
    public int insert(PartTimeJobVO partTimeJobVO) {
        PartTimeJob partTimeJob = new PartTimeJob();
        BeanUtils.copyProperties(partTimeJobVO,partTimeJob);
        partTimeJob.setCreateDate(LocalDateTime.now());
        partTimeJob.setSponsorId(UserUtils.getPrincipal());
        partTimeJob.setId(UUID.randomUUID().toString());
        partTimeJob.setSponsorId(UserUtils.getPrincipal());
        if (partTimeJobMapper.insert(partTimeJob) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public IPage<PartTimeJob> findPage(Page<PartTimeJob> page, PartTimeJobPageVO pageVO) {
        //创建查询条件
        QueryWrapper<PartTimeJob> wrapper = new QueryWrapper<>();
        //根据发起人查询
        if (pageVO.getSponsorId() != null){
            wrapper.eq("sponsor_id",pageVO.getSponsorId());
        }
        //根据兼职标题查询
        if (pageVO.getTitle() != null){
            wrapper.like("title",pageVO.getTitle());
        }
        //根据兼职内容查询
        if (pageVO.getContent() != null){
            wrapper.like("content",pageVO.getContent());
        }
        //根据兼职人数查询
        if (pageVO.getRecipientNum() != null){
            wrapper.eq("recipient_num",pageVO.getRecipientNum());
        }
        //根据兼职开始日期查询
        if (pageVO.getStartTime() != null){
            //保留
        }
        //根据兼职结束日期查询
        if (pageVO.getEndTime() != null){
            //保留
        }
        //根据工作地点查询
        if (pageVO.getWorkPlace() != null){
            wrapper.like("work_place",pageVO.getWorkPlace());
        }
        //根据工作要求查询
        if (pageVO.getJobRequirements() != null){
            wrapper.like("job_requirements",pageVO.getJobRequirements());
        }
        //根据酬劳查询
        if (pageVO.getRewarded() != null){
            wrapper.eq("rewarded",pageVO.getRewarded());
        }
        //根据备注信息查询
        if (pageVO.getRemark() != null){
            wrapper.eq("remark",pageVO.getRemark());
        }
        //根据创建时间查询
        if (pageVO.getCreateDate() != null){
            //保留
        }
        //根据更新时间查询
        if (pageVO.getUpdateDate() != null){
            //保留
        }
        //根据删除标记查询
        if (pageVO.getDelFlag() != null){
            wrapper.eq("del_flag",pageVO.getDelFlag());
        }
        return partTimeJobMapper.selectPage(page,wrapper);
    }
}
