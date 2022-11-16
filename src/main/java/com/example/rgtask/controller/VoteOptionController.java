package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.VoteOption;
import com.example.rgtask.service.VoteOptionService;
import com.example.rgtask.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/vote-option")
@Api(value = "VoteOptionController", tags = "投票选项接口")
public class VoteOptionController {
    private VoteOptionService voteOptionService;
    @Autowired
    private void setVoteOptionService(VoteOptionService voteOptionService){
        this.voteOptionService = voteOptionService;
    }

    @PostMapping("/agree/{voteOptionId}")
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
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"赞同失败");
        }
    }
}
