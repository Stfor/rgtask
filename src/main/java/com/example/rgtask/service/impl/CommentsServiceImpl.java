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
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.CommentsPageVO;
import com.example.rgtask.vo.CommentsReturnVO;
import com.example.rgtask.vo.CommentsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        comments.setUserId(UserUtils.getPrincipal());
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
    public IPage<CommentsReturnVO> findPage(CommentsPageVO pageVO) {
        IPage<Comments> iPage = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        //创建查询条件
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        //只查询最高祖宗评论
        wrapper.eq("parent_id","0");
        if (StringUtils.isNotBlank(pageVO.getAreaId())){
            wrapper.eq("area_id",pageVO.getAreaId());
        }
        if (StringUtils.isNotBlank(pageVO.getId())){
            wrapper.eq("id",pageVO.getId());
        }
        wrapper.orderByDesc("create_time");
        IPage<Comments> commentList = commentsMapper.selectPage(iPage, wrapper);
        IPage<CommentsReturnVO> commentsReturnVOList = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        commentsReturnVOList.setRecords(getAllOffspringComment(commentList.getRecords()));
        return commentsReturnVOList;
    }

    private List<CommentsReturnVO> getAllOffspringComment(List<Comments> comments){
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        List<CommentsReturnVO> commentsReturnVOS = new ArrayList<>();
        for (Comments comment : comments){
            CommentsReturnVO commentsReturn = new CommentsReturnVO();
            BeanUtils.copyProperties(comment,commentsReturn);
            wrapper.eq("parent_id",comment.getId());
            //获取所有得子评论
            List<Comments> comments1 = commentsMapper.selectList(wrapper);
            if (comments1.size() > 0){
                commentsReturn.setOffspringComments(getAllOffspringComment(comments1));
            }
            commentsReturnVOS.add(commentsReturn);
        }
        return commentsReturnVOS;
    }

}
