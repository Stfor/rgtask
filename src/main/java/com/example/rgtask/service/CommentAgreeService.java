package com.example.rgtask.service;

import com.example.rgtask.pojo.CommentAgree;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-12-11
 */
public interface CommentAgreeService extends IService<CommentAgree> {
    Boolean hadAgree(String userId,String commentId);
}
