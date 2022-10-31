package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.PartTimeJob;
import com.example.rgtask.service.PartTimeJobService;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.PartTimeJobPageVO;
import com.example.rgtask.vo.PartTimeJobVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/part-time-job")
@Api(value = "PartTimeJobController", tags = "兼职任务接口")
public class PartTimeJobController {
    private PartTimeJobService partTimeJobService;
    @Autowired
    private void setPartTimeJobService(PartTimeJobService partTimeJobService){
        this.partTimeJobService = partTimeJobService;
    }

    @PostMapping("/insert")
    public CommonResult add(@RequestBody @Validated({Create.class}) PartTimeJobVO partTimeJobVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (partTimeJobService.insert(partTimeJobVO) > 0){
            return result.success("partTimeJob",partTimeJobVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建任务失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) PartTimeJobVO partTimeJobVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        PartTimeJob partTimeJob = new PartTimeJob();
        BeanUtils.copyProperties(partTimeJobVO,partTimeJob);
        partTimeJob.setUpdateDate(LocalDateTime.now());
        if (partTimeJobService.updateById(partTimeJob)){
            return result.success("errand",partTimeJob);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新任务失败");
        }
    }

    @GetMapping("/delete/{partTimeJobId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String partTimeJobId){
        CommonResult result = new CommonResult().init();
        if (partTimeJobService.getById(partTimeJobId) == null){
            return (CommonResult) result.failCustom(-10086,"该任务不存在");
        }
        if (partTimeJobService.removeById(partTimeJobId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除任务失败");
        }
    }

    @GetMapping("/select/{partTimeJobId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String partTimeJobId){
        CommonResult result = new CommonResult().init();
        if (partTimeJobService.getById(partTimeJobId) == null){
            return (CommonResult) result.failCustom(-10086,"该任务不存在");
        }
        PartTimeJob partTimeJob = partTimeJobService.getById(partTimeJobId);
        return result.success("partTimeJob",partTimeJob);
    }

    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody PartTimeJobPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        Page<PartTimeJob> page = new Page<PartTimeJob>(pageVO.getPageNo(),pageVO.getPageSize());
        result.success("page",partTimeJobService.findPage(page, pageVO));
        result.end();
        return result;
    }
}
