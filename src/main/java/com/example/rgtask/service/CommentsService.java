package com.example.rgtask.service;

import com.example.rgtask.pojo.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.vo.CommentsVO;
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
public interface CommentsService extends IService<Comments> {
    int insert(CommentsVO commentsVO);
    List<Comments> findComments(String areaId);



}
