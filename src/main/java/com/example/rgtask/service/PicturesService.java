package com.example.rgtask.service;

import com.example.rgtask.pojo.Pictures;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.PicturesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-02
 */
public interface PicturesService extends IService<Pictures> {
    int insert(PicturesVO picturesVO);

    List<Pictures> findPictures(String areaId);
}
