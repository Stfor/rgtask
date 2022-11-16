package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.ErrandService;
import com.example.rgtask.service.PicturesService;
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
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@RequestMapping("/errand")
@CrossOrigin
@Api(value = "ErrandController", tags = "跑腿任务接口")
public class ErrandController {
    private ErrandService errandService;
    @Autowired
    private PicturesService picturesService;
    @Autowired
    private void setErrandService(ErrandService errandService){
        this.errandService = errandService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody ErrandVO errandVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (errandService.insert(errandVO) > 0){
            return result.success("errand",errandVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建任务失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) ErrandVO errandVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (errandService.updateAllById(errandVO) > 0){
            return result.success("errand",errandVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新任务失败");
        }
    }

    @GetMapping("/delete/{errandId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String errandId){
        CommonResult result = new CommonResult().init();
        if (errandService.getById(errandId) == null){
            return (CommonResult) result.failCustom(-10086,"该任务不存在");
        }
        if (errandService.removeAllById(errandId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除任务失败");
        }
    }

    @GetMapping("/select/{errandId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String errandId){
        CommonResult result = new CommonResult().init();
        if (errandService.getById(errandId) == null){
            return (CommonResult) result.failCustom(-10086,"该任务不存在");
        }
        Errand errand = errandService.getById(errandId);
        ErrandReturnVO errandReturnVO = new ErrandReturnVO();
        BeanUtils.copyProperties(errand,errandReturnVO);
        errandReturnVO.setPictures(picturesService.findPictures(errandReturnVO.getId()));
        log.info(errandReturnVO.toString());
        return result.success("errand",errandReturnVO);
    }

    @GetMapping("/receive/{errandId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult receive(@PathVariable String errandId){
        CommonResult result = new CommonResult().init();
        Errand errand = errandService.getById(errandId);
        if (errand == null){
            return (CommonResult) result.failCustom(-10086,"该任务不存在");
        }
        if (errandService.receive(errand)){
            return result.success("errand",errand);
        }else {
            return (CommonResult) result.failCustom(-10086,"接单失败");
        }
    }

    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody ErrandPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",errandService.findPage(pageVO));
        result.end();
        log.info("__________________________准备返回了");
        return result;
    }


}
