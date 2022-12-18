package com.example.rgtask.controller;


import com.example.rgtask.pojo.*;
import com.example.rgtask.service.VoteLogService;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.VoteLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-11
 */
@RestController
@Slf4j
@RequestMapping("/vote-option")
@Api(value = "VoteOptionController", tags = "投票选项接口")
public class VoteOptionController {
    private VoteOptionService voteOptionService;
    private VoteLogService voteLogService;
    @Autowired
    private void setVoteOptionService(VoteOptionService voteOptionService){
        this.voteOptionService = voteOptionService;
    }
    @Autowired
    private void setVoteLogService(VoteLogService voteLogService){
        this.voteLogService = voteLogService;
    }

//    @GetMapping("/agree/{voteOptionId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
//    })
//    public CommonResult agree(@PathVariable String voteOptionId){
//        CommonResult result = new CommonResult().init();
//        VoteOption voteOption = voteOptionService.getById(voteOptionId);
//        if (voteOption == null){
//            result.failCustom(-10086,"当前的投票选项不存在");
//        }
//        voteOption.setAgreeNum(voteOption.getAgreeNum()+1);
//        if (voteOptionService.updateById(voteOption)){
//
//            return (CommonResult) result.success();
//        }else {
//            return (CommonResult) result.failCustom(-10086,"赞同失败");
//        }
//    }

    @GetMapping("/agree/{voteOptionId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult agree(@PathVariable String voteOptionId){
        CommonResult result = new CommonResult().init();
        VoteOption voteOption = voteOptionService.getById(voteOptionId);
        if (voteOption == null){
            result.failCustom(-10086,"当前的投票选项不存在");
        }
        voteOption.setAgreeNum(voteOption.getAgreeNum()+1);
        if (voteOptionService.updateById(voteOption)){
            log.info("----------------------");
            log.info("当前得投票选项id是："+voteOptionId);
            log.info("----------------------");
            VoteOption byId = voteOptionService.getById(voteOptionId);
            voteLogService.insert(new VoteLogVO(byId.getVoteId(), UserUtils.getPrincipal(),byId.getChoice()));
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"赞同失败");
        }
    }

//    @GetMapping("/aa")
//    public void aa(){
//        String[] photos = {
//                "http://43.142.99.39:8080/pictures/202211/1.jpg",
//                "http://43.142.99.39:8080/pictures/202211/2.jpg",
//                "http://43.142.99.39:8080/pictures/202211/3.jpg",
//                "http://43.142.99.39:8080/pictures/202211/4.jpg",
//                "http://43.142.99.39:8080/pictures/202211/5.jpg"
//        };
//        List<VoteOption> list = voteOptionService.list();
//        for (VoteOption voteOption : list){
//            Random random = new Random();
//            int i = random.nextInt(5);
//            voteOption.setPicture(photos[i]);
//            voteOptionService.updateById(voteOption);
//        }
//    }
}
