package com.example.rgtask.service.impl;

import com.example.rgtask.pojo.Comments;
import com.example.rgtask.mapper.CommentsMapper;
import com.example.rgtask.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-02
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
