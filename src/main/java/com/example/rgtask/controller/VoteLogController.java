package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.service.VoteLogService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandReturnVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.VoteLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@Controller
@RequestMapping("/vote-log")
@Api(value = "VoteController", tags = "投票接口")
@RestController
@CrossOrigin
public class VoteLogController {
    private VoteLogService voteLogService;
    @Autowired
    private void setVoteLogService(VoteLogService voteLogService){
        this.voteLogService = voteLogService;
    }
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody VoteLogVO voteLogVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (voteLogService.insert(voteLogVO)){
            return result.success("voteLog",voteLogVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建投票日志");
        }
    }

//    @PostMapping("/update")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
//    })
//    public CommonResult update(@RequestBody @Validated({Update.class}) ErrandVO errandVO, BindingResult bindingResult){
//        CommonResult result = new CommonResult().init();
//        //参数验证
//        if (bindingResult.hasErrors()) {
//            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
//        }
//        if (errandService.updateAllById(errandVO) > 0){
//            return result.success("errand",errandVO);
//        }else {
//            return (CommonResult) result.failCustom(-10086,"更新任务失败");
//        }
//    }

    @GetMapping("/delete/{voteLogId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String voteLogId){
        CommonResult result = new CommonResult().init();
        if (voteLogService.getById(voteLogId) == null){
            return (CommonResult) result.failCustom(-10086,"该投票日志不存在");
        }
        if (voteLogService.removeById(voteLogId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除投票日志失败");
        }
    }

//    @GetMapping("/select/{errandId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
//    })
//    public CommonResult select(@PathVariable String errandId){
//        CommonResult result = new CommonResult().init();
//        if (errandService.getById(errandId) == null){
//            return (CommonResult) result.failCustom(-10086,"该任务不存在");
//        }
//        Errand errand = errandService.getById(errandId);
//        ErrandReturnVO errandReturnVO = new ErrandReturnVO();
//        BeanUtils.copyProperties(errand,errandReturnVO);
//        errandReturnVO.setPictures(picturesService.findPictures(errandReturnVO.getId()));
//        log.info(errandReturnVO.toString());
//        return result.success("errand",errandReturnVO);
//    }


//    @PostMapping("findPage")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
//    })
//    public CommonResult findPage(@RequestBody ErrandPageVO pageVO, BindingResult bindingResult){
//        CommonResult result = new CommonResult().init();
//        //参数验证
//        if (bindingResult.hasErrors()) {
//            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
//            return result;
//        }
//        result.success("page",voteLogService.findPage(pageVO));
//        result.end();
//        return result;
//    }
}
