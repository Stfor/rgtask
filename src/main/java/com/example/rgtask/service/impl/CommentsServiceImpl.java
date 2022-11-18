package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Comments;
import com.example.rgtask.mapper.CommentsMapper;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.vo.CommentsPageVO;
import com.example.rgtask.vo.CommentsVO;
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
 * @since 2022-11-02
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    private CommentsMapper commentsMapper;

    @Autowired
    private void setCommentsMapper(CommentsMapper commentsMapper){this.commentsMapper = commentsMapper;}

    @Override
    public int insert(CommentsVO commentsVO){
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsVO,comments);
        comments.setId(UUID.randomUUID().toString());
        comments.setCreateTime(LocalDateTime.now());
        if (commentsMapper.insert(comments)>0){
            return 1;
        }else{
            return 0;
        }
    }


    @Override
    public List<Comments> findComments(String areaId){
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        wrapper.eq("area_id",areaId);
        return commentsMapper.selectList(wrapper);
    }

    @Override
    public IPage<Comments> findPage(Page<Comments> page, CommentsPageVO pageVO) {
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        String id = pageVO.getId();
        if(StringUtils.isNotBlank(id)){
            wrapper.eq("parent_id",id);
        }
        return commentsMapper.selectPage(page,wrapper);
    }

}
