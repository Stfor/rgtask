package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.IdleGoods;
import com.example.rgtask.mapper.IdleGoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.IdleGoodsService;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.IdleGoodsPageVO;
import com.example.rgtask.vo.IdleGoodsVO;
import com.example.rgtask.vo.PicturesVO;
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
        goods.setStatus("0");
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
    public int modify(IdleGoodsVO idleGoodsVO) {
        IdleGoods goods = new IdleGoods();
        BeanUtils.copyProperties(idleGoodsVO,goods);
        goods.setUpdateDate(LocalDateTime.now());
        if(idleGoodsMapper.updateById(goods) > 0){
            //修改图片
            picturesService.removeByAreaId(goods.getId());
            List<String> pictures = idleGoodsVO.getPictures();
            if(pictures != null){
                for (String picture : pictures) {
                    picturesService.insert(new PicturesVO(goods.getId(),picture));
                }
            }
            return 1;
        }
        else return 0;

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
    public IPage<IdleGoodsVO> findPage(IdleGoodsPageVO pageVO) {
        Page<IdleGoods> page = new Page<IdleGoods>(pageVO.getPageNo(),pageVO.getPageSize());
        LambdaQueryWrapper<IdleGoods> wrapper = new LambdaQueryWrapper();

        //根据发起人查询
        if(pageVO.getSponsorId()!=null){
            wrapper.eq(IdleGoods::getSponsorId,pageVO.getSponsorId());;
        }
        if (StringUtils.isNotBlank(pageVO.getRecipientId())){
            wrapper.eq(IdleGoods::getRecipientId,pageVO.getRecipientId());;
        }
        if(pageVO.getTitle() != null){
            wrapper.like(IdleGoods::getTitle,pageVO.getTitle());
        }

        if(pageVO.getLabel() != null){
            wrapper.like(IdleGoods::getLabel,pageVO.getLabel());
        }

        if(pageVO.getGoodsName() != null){
            wrapper.like(IdleGoods::getGoodsName,pageVO.getGoodsName());
        }

        if(pageVO.getDescription() != null){
            wrapper.like(IdleGoods::getDescription,pageVO.getDescription());
        }
        if(pageVO.getRemark() != null){
            wrapper.like(IdleGoods::getRemark,pageVO.getRemark());
        }


        wrapper.orderByDesc(IdleGoods::getCreateDate);
        IPage<IdleGoods> idleGoodsPage = idleGoodsMapper.selectPage(page, wrapper);
        IPage<IdleGoodsVO> idleGoodsVOPage = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        BeanUtils.copyProperties(idleGoodsPage,idleGoodsVOPage);

        List<IdleGoods> idleGoodsList = idleGoodsPage.getRecords();
        List<IdleGoodsVO> idleGoodsVOList = new ArrayList<>();
        //复制
        for (IdleGoods idleGoods : idleGoodsList) {
            IdleGoodsVO idleGoodsVO = new IdleGoodsVO();
            BeanUtils.copyProperties(idleGoods,idleGoodsVO);
            idleGoodsVOList.add(idleGoodsVO);
        }
        //添加图片 以及添加头像
        for(IdleGoodsVO vo : idleGoodsVOList){
            vo.setPictures(picturesService.findPictures(vo.getId()));
            vo.setAvatar(UserUtils.getUserAvatarFromRedis(vo.getSponsorId()));
        }
        idleGoodsVOPage.setRecords(idleGoodsVOList);
        return idleGoodsVOPage;

    }
}
