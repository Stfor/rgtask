package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.IdleGoods;
import com.example.rgtask.mapper.IdleGoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.IdleGoodsService;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.IdleGoodsPageVO;
import com.example.rgtask.vo.IdleGoodsVO;
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
public class IdleGoodsServiceImpl extends ServiceImpl<IdleGoodsMapper, IdleGoods> implements IdleGoodsService {


    private IdleGoodsMapper idleGoodsMapper;
    @Autowired
    private void setIdleGoodsMapper(IdleGoodsMapper idleGoodsMapper){
        this.idleGoodsMapper=idleGoodsMapper;

    }

    private PicturesService picturesService;
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService=picturesService;
    }

    @Override
    public int insert(IdleGoodsVO idleGoodsVO) {
        IdleGoods goods = new IdleGoods();
        BeanUtils.copyProperties(idleGoodsVO,goods);
        goods.setId(UUID.randomUUID().toString());
        goods.setCreateDate(LocalDateTime.now());
        goods.setSponsorId(UserUtils.getPrincipal());
        if(idleGoodsMapper.insert(goods) > 0 ) {
            if (picturesService.insertList(idleGoodsVO.getPictures(), goods.getId()))
            {
                return 1;
            }
            else{
                return 0;
            }
        }
         return 0;
    }

    @Override
    public boolean modify(IdleGoods idleGoods) {
        return idleGoodsMapper.updateById(idleGoods) > 0;
    }

    @Override
    public boolean removeAllById(String id) {
        if(idleGoodsMapper.deleteById(id) >0){
            if(picturesService.removeByAreaId(id) > 0){
                return true;
            }else {
                return false;
            }
        }
        else return false;
    }


    @Override
    public IPage<IdleGoods> findPage(Page<IdleGoods> page, IdleGoodsPageVO idleGoods) {
        LambdaQueryWrapper<IdleGoods> wrapper = new LambdaQueryWrapper();

        if(idleGoods.getSponsorId()!=null){
            wrapper.eq(IdleGoods::getSponsorId,idleGoods.getSponsorId());;
        }

        if(idleGoods.getTitle() != null){
            wrapper.like(IdleGoods::getTitle,idleGoods.getTitle());
        }

        if(idleGoods.getLabel() != null){
            wrapper.like(IdleGoods::getLabel,idleGoods.getGoodsName());
        }

        if(idleGoods.getGoodsName() != null){
            wrapper.like(IdleGoods::getGoodsName,idleGoods.getGoodsName());
        }

        if(idleGoods.getDescription() != null){
            wrapper.like(IdleGoods::getDescription,idleGoods.getDescription());
        }
        if(idleGoods.getRemark() != null){
            wrapper.like(IdleGoods::getRemark,idleGoods.getRemark());
        }
        return idleGoodsMapper.selectPage(page,wrapper);
    }
}
