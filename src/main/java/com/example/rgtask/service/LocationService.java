package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Comments;
import com.example.rgtask.pojo.Location;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.CommentsPageVO;
import com.example.rgtask.vo.LocationPageVO;
import com.example.rgtask.vo.LocationVO;
import com.example.rgtask.vo.PicturesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
public interface LocationService extends IService<Location> {

    int insert(LocationVO locationVO);

    IPage<Location> findPage(LocationPageVO pageVO);
    List<Location> findLocation(String id);

    Boolean compareLocation(LocationVO locationVO);

}
