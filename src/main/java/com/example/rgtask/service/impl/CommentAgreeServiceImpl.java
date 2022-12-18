package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.CommentAgree;
import com.example.rgtask.mapper.CommentAgreeMapper;
import com.example.rgtask.service.CommentAgreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-12-11
 */
@Service
public class CommentAgreeServiceImpl extends ServiceImpl<CommentAgreeMapper, CommentAgree> implements CommentAgreeService {
    private CommentAgreeMapper commentAgreeMapper;
    @Autowired
    private void setCommentAgreeMapper(CommentAgreeMapper commentAgreeMapper){
        this.commentAgreeMapper = commentAgreeMapper;
    }
    @Override
    public Boolean hadAgree(String userId, String commentId) {
        QueryWrapper<CommentAgree> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("comment_id",commentId);
        return commentAgreeMapper.selectCount(wrapper) > 0;
    }
}
