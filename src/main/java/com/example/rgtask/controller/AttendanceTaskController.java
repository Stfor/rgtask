package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.service.AttendanceTaskService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.*;
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
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/attendance-task")
@CrossOrigin
@Api(value = "AttendanceTaskController", tags = "晚点名任务接口")
public class AttendanceTaskController {
    private AttendanceTaskService attendanceTaskService;
    @Autowired
    private void setAttendanceTaskService(AttendanceTaskService attendanceTaskService){
        this.attendanceTaskService = attendanceTaskService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody AttendanceTaskVO attendanceTaskVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (attendanceTaskService.insert(attendanceTaskVO)){
            return result.success("attendanceTask",attendanceTaskVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建晚点名任务失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) AttendanceTaskVO attendanceTaskVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (attendanceTaskService.updateAllById(attendanceTaskVO)){
            return result.success("attendanceTask",attendanceTaskVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新晚点名任务失败");
        }
    }

    @GetMapping("/delete/{attendanceTaskId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String attendanceTaskId){
        CommonResult result = new CommonResult().init();
        if (attendanceTaskService.getById(attendanceTaskId) == null){
            return (CommonResult) result.failCustom(-10086,"该晚点名任务不存在");
        }
        if (attendanceTaskService.removeById(attendanceTaskId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除晚点名任务失败");
        }
    }

    @GetMapping("/select/{attendanceTaskId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String attendanceTaskId){
        CommonResult result = new CommonResult().init();
        if (attendanceTaskService.getById(attendanceTaskId) == null){
            return (CommonResult) result.failCustom(-10086,"该晚点名任务不存在");
        }
        return result.success("attendanceTask",attendanceTaskService.getById(attendanceTaskId));
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody AttendanceTaskPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",attendanceTaskService.findPage(pageVO)).end();
        return result;
    }

}
