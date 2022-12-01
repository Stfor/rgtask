package com.example.rgtask.service;

import com.example.rgtask.pojo.FaceInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.FaceInformationVO;

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
}
