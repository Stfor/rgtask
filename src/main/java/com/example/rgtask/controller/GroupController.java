package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.GroupService;
import com.example.rgtask.service.GroupUserService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.AttendanceTaskPageVO;
import com.example.rgtask.vo.AttendanceTaskVO;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/group")
@CrossOrigin
@Api(value = "GroupController", tags = "分组接口")
public class GroupController {
    private GroupService groupService;
    private GroupUserService groupUserService;
    @Autowired
    private void setGroupService(GroupService groupService){
        this.groupService = groupService;
    }
    @Autowired
    private void setGroupUserService(GroupUserService groupUserService){
        this.groupUserService = groupUserService;
    }


    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody GroupVO groupVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (groupService.insert(groupVO)){
            return result.success("group",groupVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建分组失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) GroupVO groupVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (groupService.updateAllById(groupVO)){
            return result.success("group",groupVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新分组失败");
        }
    }

    @GetMapping("/delete/{groupId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String groupId){
        CommonResult result = new CommonResult().init();
        if (groupService.getById(groupId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组不存在");
        }
        if (groupService.removeAllById(groupId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除分组失败");
        }
    }

    @GetMapping("/select/{groupId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String groupId){
        CommonResult result = new CommonResult().init();
        if (groupService.getById(groupId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组不存在");
        }
        return result.success("group",groupService.getById(groupId));
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody GroupPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",groupService.findPage(pageVO)).end();
        return result;
    }
}
