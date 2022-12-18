package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.pojo.Vote;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.service.VoteLogService;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Slf4j
@RestController
@RequestMapping("/vote")
@Transactional
@Api(value = "VoteController", tags = "投票接口")
public class VoteController {
    private VoteOptionService voteOptionService;
    private VoteService voteService;
    private PicturesService picturesService;
    private VoteLogService voteLogService;
    @Autowired
    private void setVoteOptionService(VoteOptionService voteOptionService){
        this.voteOptionService = voteOptionService;
    }
    @Autowired
    private void setVoteService(VoteService voteService){
        this.voteService = voteService;
    }
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService = picturesService;
    }
    @Autowired
    private void  setVoteLogService(VoteLogService voteLogService){
        this.voteLogService = voteLogService;
    }


    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult insert(@RequestBody @Validated({Create.class}) VoteVO voteVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (voteService.insert(voteVO) > 0){
            return result.success("vote",voteVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建投票失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) VoteVO voteVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (voteService.updateAllById(voteVO) > 0){
            return result.success("vote",voteVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新投票失败");
        }
    }

    @GetMapping("/delete/{voteId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String voteId){
        CommonResult result = new CommonResult().init();
        if (voteService.getById(voteId) == null){
            return (CommonResult) result.failCustom(-10086,"该选项不存在");
        }
        if (voteService.removeAllById(voteId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除选项失败");
        }
    }

    @GetMapping("/select/{voteId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String voteId){
        CommonResult result = new CommonResult().init();
        if (voteService.getById(voteId) == null){
            return (CommonResult) result.failCustom(-10086,"该投票不存在");
        }
        Vote vote = voteService.getById(voteId);
        VoteReturnVO vo = new VoteReturnVO();
        BeanUtils.copyProperties(vote,vo);
        vo.setPictures(picturesService.findPictures(voteId));
        vo.setVoteOptionVOList(voteOptionService.findVoteOptionByAreaId(voteId));
        vo.setAvatar(UserUtils.getUserAvatarFromRedis(vo.getUserId()));
        return result.success("vote",vo);
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody VotePageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",voteService.findPage(pageVO));
        result.end();
        return result;
    }


    @PostMapping("myVoted")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult myVoted(@RequestBody MyVotedVO myVotedVO){
        if (myVotedVO!=null){
            log.info(myVotedVO.getUserId()+"------------------------"+myVotedVO.getLabel());
        }

        CommonResult result = new CommonResult().init();
        return result.success("voted",voteService.getVotedByUserId(myVotedVO.getUserId(),myVotedVO.getLabel()));
    }

    @GetMapping("ifVoted/{userId}/{voteId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult ifVoted(@PathVariable String userId,@PathVariable String voteId){
        CommonResult result = new CommonResult().init();
        if (voteLogService.hadVoted(userId,voteId)){
            return result.success("ifVoted",true);
        }else {
            return result.success("ifVoted",false);
        }
    }

//    @GetMapping("/aa")
//    public void aa(){
//        List<Vote> list = voteService.list();
//        for (Vote vote : list){
//            List<Pictures> pictures = picturesService.getByAreaId(vote.getId());
//            if (pictures.size() == 0){
//                picturesService.insert(new PicturesVO(vote.getId(),"http://43.142.99.39:8080/pictures/202211/3.jpg"));
//                picturesService.insert(new PicturesVO(vote.getId(),"http://43.142.99.39:8080/pictures/202211/3.jpg"));
//            }else if (pictures.size() == 1){
//                picturesService.insert(new PicturesVO(vote.getId(),"http://43.142.99.39:8080/pictures/202211/3.jpg"));
//            }
//        }
//    }
}
