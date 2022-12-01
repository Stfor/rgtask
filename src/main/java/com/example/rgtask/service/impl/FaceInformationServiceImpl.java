package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.FaceInformation;
import com.example.rgtask.mapper.FaceInformationMapper;
import com.example.rgtask.service.FaceInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.utils.FaceUtils;
import com.example.rgtask.vo.FaceInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean authenticated(FaceInformationVO faceInformationVO) throws Exception {
        QueryWrapper<FaceInformation> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",faceInformationVO.getUserid());
        FaceInformation faceInformation = faceInformationMapper.selectOne(wrapper);
        return Double.parseDouble(FaceUtils.execute(faceInformation.getPicture(), faceInformationVO.getPicture())) > 0.95;
    }
}
