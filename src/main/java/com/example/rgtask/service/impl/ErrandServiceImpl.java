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
import com.example.rgtask.vo.*;
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
        errand.setSponsorId(UserUtils.getPrincipal());
        errand.setId(UUID.randomUUID().toString());
        errand.setSponsorId(UserUtils.getPrincipal());
        errand.setStatus("0");
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
            //修改图片信息
            picturesService.removeByAreaId(errand.getId());
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
    public Boolean removeAllById(String errandId) {
        if (errandMapper.deleteById(errandId) > 0){
            picturesService.removeByAreaId(errandId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean receive(Errand errand) {
        errand.setRecipientId(UserUtils.getPrincipal());
        errand.setStatus("1");
        errand.setUpdateDate(LocalDateTime.now());
        if (errandMapper.updateById(errand) > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public IPage<ErrandReturnVO> findPage( ErrandPageVO pageVO) {
        Page<Errand> page = new Page<Errand>(pageVO.getPageNo(),pageVO.getPageSize());
        //创建查询条件
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();

//        //根据发起人查询
//        if (StringUtils.isNotEmpty(pageVO.getSponsorId())){
//            wrapper.eq("university",UserUtils.getUser().getUniversity());
//        }
        //根据发起人查询
        if (StringUtils.isNotEmpty(pageVO.getSponsorId())){
            wrapper.eq("sponsor_id",pageVO.getSponsorId());
        }
        //根据接收人查询
        if (StringUtils.isNotEmpty(pageVO.getRecipientId())){
            wrapper.eq("recipient_id",pageVO.getRecipientId());
        }
        //根据标题查询
        if (StringUtils.isNotEmpty(pageVO.getTitle())){
            wrapper.like("title",pageVO.getTitle());
        }
        //根据内容查询
        if (StringUtils.isNotEmpty(pageVO.getContent())){
            wrapper.like("content",pageVO.getContent());
        }
        //根据标签查询
        if (StringUtils.isNotEmpty(pageVO.getLabel())){
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
        if (StringUtils.isNotEmpty(pageVO.getTaskAddress())){
            wrapper.like("task_address",pageVO.getTaskAddress());
        }
        //根据送达地址查询
        if (StringUtils.isNotEmpty(pageVO.getDeliveryAddress())){
            wrapper.like("delivery_address",pageVO.getDeliveryAddress());
        }
        //根据状态查询
        if (StringUtils.isNotEmpty(pageVO.getStatus())){
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
        if (StringUtils.isNotEmpty(pageVO.getDelFlag())){
            wrapper.eq("del_flag",pageVO.getDelFlag());
        }
        //根据备注查询
        if (StringUtils.isNotEmpty(pageVO.getRemark())){
            wrapper.eq("remark",pageVO.getRemark());
        }
        wrapper.orderByDesc("create_date");
        IPage<Errand> errandPage = errandMapper.selectPage(page, wrapper);
        IPage<ErrandReturnVO> errandReturnVOPage = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        BeanUtils.copyProperties(errandPage,errandReturnVOPage);

        List<Errand> errandList = errandPage.getRecords();
        List<ErrandReturnVO> errandReturnVOList = new ArrayList<>();
        //复制所有的Errand
        for (Errand errand : errandList){
            ErrandReturnVO returnVO = new ErrandReturnVO();
            BeanUtils.copyProperties(errand,returnVO);
            errandReturnVOList.add(returnVO);
        }
        //为所有的ErrandReturnVO添加图片以及头像
        for (ErrandReturnVO vo : errandReturnVOList){
            vo.setPictures(picturesService.findPictures(vo.getId()));
            vo.setAvatar(UserUtils.getUserAvatarFromRedis(vo.getSponsorId()));
        }
        errandReturnVOPage.setRecords(errandReturnVOList);
        return errandReturnVOPage;
    }

}
