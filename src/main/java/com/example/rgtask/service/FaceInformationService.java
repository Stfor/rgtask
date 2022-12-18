package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.FaceInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.FaceInformationPageVO;
import com.example.rgtask.vo.FaceInformationReturnVO;
import com.example.rgtask.vo.FaceInformationVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
public interface FaceInformationService extends IService<FaceInformation> {
    Boolean authenticated(FaceInformationVO faceInformationVO) throws Exception;

    int insert(FaceInformationVO faceinformationVO);

    int updateAllById(FaceInformationVO faceinformationVO);

    boolean removeAllById(String id);

    IPage<FaceInformation> findPage(FaceInformationPageVO pageVO);

    List<FaceInformationReturnVO> getFaceInformationByUserId(String userId);

    Integer getCountByUserId(String userId);
}
