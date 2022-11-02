package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.mapper.ErrandMapper;
import com.example.rgtask.service.ErrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.UserPageVO;
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
public class ErrandServiceImpl extends ServiceImpl<ErrandMapper, Errand> implements ErrandService {
    private ErrandMapper errandMapper;
    @Autowired
    private void setErrandService(ErrandMapper errandMapper){
        this.errandMapper = errandMapper;
    }

    @Override
    public int insert(ErrandVO errandVO) {
        Errand errand = new Errand();
        BeanUtils.copyProperties(errandVO,errand);
        errand.setCreateDate(LocalDateTime.now());
        errand.setDelFlag("1");
        errand.setRecipientId(UserUtils.getPrincipal());
        errand.setId(UUID.randomUUID().toString());
        if (errandMapper.insert(errand) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public IPage<Errand> findPage(Page<Errand> page, ErrandPageVO pageVO) {
        //创建查询条件
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();

        //根据发起人查询
        if (pageVO.getSponsorId() != null){
            wrapper.eq("sponsor_id",pageVO.getSponsorId());
        }
        //根据接收人查询
        if (pageVO.getRecipientId() != null){
            wrapper.eq("recipient_id",pageVO.getRecipientId());
        }
        //根据标题查询
        if (pageVO.getTitle() != null){
            wrapper.like("title",pageVO.getTitle());
        }
        //根据内容查询
        if (pageVO.getContent() != null){
            wrapper.like("content",pageVO.getContent());
        }
        //根据标签查询
        if (pageVO.getLabel() != null){
            wrapper.eq("label",pageVO.getLabel());
        }
        //根据开始日期查询
        if (pageVO.getStartDate() != null){
            //暂时保留
        }
        //根据结束日期查询
        if (pageVO.getEndDate() != null){
            //暂时保留
        }
        //根据取货地址查询
        if (pageVO.getTaskAddress() != null){
            wrapper.like("task_address",pageVO.getTaskAddress());
        }
        //根据送达地址查询
        if (pageVO.getDeliveryAddress() != null){
            wrapper.like("delivery_address",pageVO.getDeliveryAddress());
        }
        //根据状态查询
        if (pageVO.getStatus() != null){
            wrapper.eq("status",pageVO.getStatus());
        }
        //根据酬劳查询
        if (pageVO.getRewarded() != null){
            wrapper.eq("rewarded",pageVO.getRewarded());
        }
        //根据创建时间查询
        if (pageVO.getCreateDate() != null){
            //暂时保留
        }
        //根据更新时间查询
        if (pageVO.getUpdateDate() != null){
            //暂时保留
        }
        //根据删除标记查询
        if (pageVO.getDelFlag() != null){
            wrapper.eq("del_flag",pageVO.getDelFlag());
        }
        //根据备注查询
        if (pageVO.getRemark() != null){
            wrapper.eq("remark",pageVO.getRemark());
        }
        return errandMapper.selectPage(page,wrapper);
    }
}