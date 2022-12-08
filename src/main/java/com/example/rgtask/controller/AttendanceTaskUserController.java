package com.example.rgtask.controller;


import com.example.rgtask.pojo.AttendanceTaskUser;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.AttendanceTaskUserService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.AttendanceTaskUserPageVO;
import com.example.rgtask.vo.AttendanceTaskUserVO;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupVO;
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
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/attendance-task-user")
@CrossOrigin
@Api(value = "AttendanceTaskUserController", tags = "用户-任务接口")
public class AttendanceTaskUserController {
    private AttendanceTaskUserService attendanceTaskUserService;
    @Autowired
    private void setAttendanceTaskUserService(AttendanceTaskUserService attendanceTaskUserService){
        this.attendanceTaskUserService = attendanceTaskUserService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody AttendanceTaskUserVO attendanceTaskUserVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (attendanceTaskUserService.insert(attendanceTaskUserVO)){
            return result.success("attendanceTaskUser",attendanceTaskUserVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建用户签到失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) AttendanceTaskUserVO attendanceTaskUserVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        AttendanceTaskUser attendanceTaskUser = new AttendanceTaskUser();
        BeanUtils.copyProperties(attendanceTaskUserVO,attendanceTaskUser);
        if (attendanceTaskUserService.updateById(attendanceTaskUser)){
            return result.success("attendanceTaskUser",attendanceTaskUserVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新用户签到失败");
        }
    }

    @GetMapping("/delete/{attendanceTaskUserId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String attendanceTaskUserId){
        CommonResult result = new CommonResult().init();
        if (attendanceTaskUserService.getById(attendanceTaskUserId) == null){
            return (CommonResult) result.failCustom(-10086,"该用户签到不存在");
        }
        if (attendanceTaskUserService.removeById(attendanceTaskUserId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除用户签到失败");
        }
    }

    @GetMapping("/select/{attendanceTaskUserId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String attendanceTaskUserId){
        CommonResult result = new CommonResult().init();
        if (attendanceTaskUserService.getById(attendanceTaskUserId) == null){
            return (CommonResult) result.failCustom(-10086,"该用户签到不存在");
        }
        return result.success("attendanceTaskUser",attendanceTaskUserService.getById(attendanceTaskUserId));
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody AttendanceTaskUserPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",attendanceTaskUserService.findPage(pageVO)).end();
        return result;
    }
}
