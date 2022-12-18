package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.*;
import com.example.rgtask.service.CommentAgreeService;
import com.example.rgtask.service.CommentsService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.CommentsPageVO;
import com.example.rgtask.vo.CommentsVO;
import com.example.rgtask.vo.ErrandPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-02
 */
@RestController
@RequestMapping("/comments")
@Api(value = "CommentsController", tags = "评论接口")
public class CommentsController {
    private CommentsService commentsService;
    private CommentAgreeService commentAgreeService;

    @Autowired
    private void setCommentsService(CommentsService commentsService){this.commentsService = commentsService;}
    @Autowired
    private void setCommentAgreeService(CommentAgreeService commentAgreeService){
        this.commentAgreeService = commentAgreeService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody @Validated({Create.class})CommentsVO commentsVO, BindingResult bingingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if(bingingResult.hasErrors()){
            return (CommonResult) result.failIllegalArgument(bingingResult.getFieldErrors()).end();
            }
            if(commentsService.insert(commentsVO) > 0){
                return result.success("comments",commentsVO);
            }else {
                return (CommonResult) result.failCustom(-10086,"添加评论失败");
            }
        }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token",value = "访问token",paramType = "header",dataType = "string",required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) CommentsVO commentsVO,BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if(bindingResult.hasErrors()){
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsVO,comments);
        comments.setCreateTime(LocalDateTime.now());
        if(commentsService.updateById(comments)){
            return result.success("comments",comments);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新评论失败");
        }
    }
    @GetMapping("/delete/{commentsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String commentsId){
        CommonResult result = new CommonResult().init();
        if (commentsService.getById(commentsId) == null){
            return (CommonResult) result.failCustom(-10086,"该评论不存在");
        }
        if (commentsService.removeById(commentsId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除评论失败");
        }
    }
    @GetMapping("/select/{commentsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String commentsId){
        CommonResult result = new CommonResult().init();
        if (commentsService.getById(commentsId) == null){
            return (CommonResult) result.failCustom(-10086,"该评论不存在");
        }
        Comments comments = commentsService.getById(commentsId);
        return result.success("comments",comments);
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody CommentsPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",commentsService.findPage(pageVO));
        result.end();
        return result;
    }

    @GetMapping("/agree/{commentId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult agree(@PathVariable String commentId){
        CommonResult result = new CommonResult().init();
        Comments comment = commentsService.getById(commentId);
        if (commentAgreeService.hadAgree(UserUtils.getPrincipal(),commentId)){
            return (CommonResult) result.failCustom(-10086,"不可重复点赞");
        }
        if (commentsService.updateById(new Comments(commentId,comment.getThumbsUp()+1))){
            if (commentAgreeService.save(new CommentAgree(commentId, UserUtils.getPrincipal()))){
                return (CommonResult) result.success();
            }else {
                return (CommonResult) result.failCustom(-10086,"点赞失败");
            }
        }
        return (CommonResult) result.failCustom(-10086,"点赞失败");
    }

    @GetMapping("/aa")
    public void aa(){
        List<Comments> list = commentsService.list();
        for (Comments comments : list){
            if (comments.getAreaId() == null){
                comments.setAreaId(commentsService.getById(comments.getParentId()).getAreaId());
                commentsService.updateById(comments);
            }
        }
    }
}


