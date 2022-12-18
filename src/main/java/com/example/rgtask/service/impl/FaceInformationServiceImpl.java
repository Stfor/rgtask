package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.FaceInformation;
import com.example.rgtask.mapper.FaceInformationMapper;
import com.example.rgtask.service.FaceInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.FaceUtils;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.FaceInformationPageVO;
import com.example.rgtask.vo.FaceInformationReturnVO;
import com.example.rgtask.vo.FaceInformationVO;
import com.example.rgtask.vo.PicturesVO;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2022-11-18
 */
@Service
public class FaceInformationServiceImpl extends ServiceImpl<FaceInformationMapper, FaceInformation> implements FaceInformationService {
    private FaceInformationMapper faceInformationMapper;
    @Autowired
    private void setFaceInformationMapper(FaceInformationMapper faceInformationMapper){
        this.faceInformationMapper = faceInformationMapper;
    }
    private PicturesService picturesService;
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService=picturesService;
    }

    @Override
    public int insert(FaceInformationVO faceinformationVO) {
        FaceInformation faceinformation = new FaceInformation();
        BeanUtils.copyProperties(faceinformationVO,faceinformation);
        faceinformation.setCreateDate(LocalDateTime.now());
        faceinformation.setId(UUID.randomUUID().toString());
        faceinformation.setUserid(UserUtils.getPrincipal());
        if (faceInformationMapper.insert(faceinformation) > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int updateAllById(FaceInformationVO faceinformationVO) {
        FaceInformation face = new FaceInformation();
        BeanUtils.copyProperties(faceinformationVO,face);
        face.setUpdateTime(LocalDateTime.now());
        if(faceInformationMapper.updateById(face) > 0){
            //修改图片
            picturesService.removeByAreaId(face.getId());
            String pictures = faceinformationVO.getPicture();
            if(pictures != null){
                String picture = null;
                picturesService.insert(new PicturesVO(face.getId(),picture));
            }
            return 1;
        }
        else return 0;
    }

    @Override
    public boolean removeAllById(String id) {
        if(faceInformationMapper.deleteById(id) >0){
            if(picturesService.removeByAreaId(id) > 0){
                return true;
            }else {
                return false;
            }
        }
        else return false;
    }

    @Override
    public IPage<FaceInformation> findPage(FaceInformationPageVO pageVO) {
        Page<FaceInformation> page = new Page<FaceInformation>(pageVO.getPageNo(),pageVO.getPageSize());

        QueryWrapper<FaceInformation> wrapper = new QueryWrapper<>();
        // 根据id查询
        if (StringUtils.isNotEmpty(pageVO.getId())){
            wrapper.eq("id", pageVO.getId());
        }
        // 用户id查询
        if (StringUtils.isNotEmpty(pageVO.getUserid())){
            wrapper.eq("userId",pageVO.getUserid());
        }
        return faceInformationMapper.selectPage(page,wrapper);
    }

    @Override
    public List<FaceInformationReturnVO> getFaceInformationByUserId(String userId) {
        return null;
    }

    @Override
    public Integer getCountByUserId(String userId) {
        QueryWrapper<FaceInformation> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        return faceInformationMapper.selectCount(wrapper);
    }

    @Override
    public Boolean authenticated(FaceInformationVO faceInformationVO) throws Exception {
        QueryWrapper<FaceInformation> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(faceInformationVO.getUserid())){
            wrapper.eq("userId",faceInformationVO.getUserid());
        }else {
            wrapper.eq("userId", UserUtils.getPrincipal());
        }
        FaceInformation faceInformation = faceInformationMapper.selectOne(wrapper);
        return Double.parseDouble(FaceUtils.execute(faceInformation.getPicture(), faceInformationVO.getPicture())) > 0.95;
    }
}
