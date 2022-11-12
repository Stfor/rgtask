package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.mapper.ErrandMapper;
import com.example.rgtask.service.ErrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.PicturesVO;
import com.example.rgtask.vo.UserPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
@Service
public class ErrandServiceImpl extends ServiceImpl<ErrandMapper, Errand> implements ErrandService {
    private ErrandMapper errandMapper;
    private PicturesService picturesService;
    @Autowired
    private void setErrandService(ErrandMapper errandMapper){
        this.errandMapper = errandMapper;
    }
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService = picturesService;
    }
    @Override
    public int insert(ErrandVO errandVO) {
        Errand errand = new Errand();
        BeanUtils.copyProperties(errandVO,errand);
        errand.setCreateDate(LocalDateTime.now());
        errand.setDelFlag("0");
        errand.setSponsorId(UserUtils.getPrincipal());
        errand.setId(UUID.randomUUID().toString());
        //插入兼职的图片
        if (errandMapper.insert(errand) > 0){
            List<String> pictures = errandVO.getPictures();
            if (pictures != null){
                for (String picture : pictures){
                    picturesService.insert(new PicturesVO(errand.getId(),picture));
                }
            }
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int updateAllById(ErrandVO errandVO) {
        Errand errand = new Errand();
        BeanUtils.copyProperties(errandVO,errand);
        errand.setUpdateDate(LocalDateTime.now());
        if (errandMapper.updateById(errand) > 0){
            picturesService.removeByAreaId(errand.getId());
            List<String> pictures = errandVO.getPictures();
            for (String picture : pictures){
                picturesService.insert(new PicturesVO(errand.getId(),picture));
            }
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Boolean removeAllById(String errandId) {
        if (errandMapper.deleteById(errandId) > 0){
            picturesService.removeByAreaId(errandId);
            return true;
        }else {
            return false;
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
