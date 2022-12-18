package com.example.rgtask.controller;


import com.example.rgtask.pojo.AttendanceTask;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.GroupUser;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.*;
import com.example.rgtask.utils.UserUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/group-user")
@CrossOrigin
@Api(value = "GroupUserController", tags = "分组成员接口")
public class GroupUserController {
    private GroupUserService groupUserService;
    private GroupService groupService;
    private UserService userService;
    private AttendanceTaskService attendanceTaskService;
    private AttendanceTaskUserService attendanceTaskUserService;
    @Autowired
    private void setGroupUserService(GroupUserService groupUserService){
        this.groupUserService = groupUserService;
    }
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    private void setGroupService(GroupService groupService){
        this.groupUserService = groupUserService;
    }
    @Autowired
    private void setAttendanceTaskService(AttendanceTaskService attendanceTaskService){
        this.attendanceTaskService = attendanceTaskService;
    }
    @Autowired
    private void setAttendanceTaskUserService(AttendanceTaskUserService attendanceTaskUserService){
        this.attendanceTaskUserService = attendanceTaskUserService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody GroupUserVO groupUserVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (groupUserVO.getUserId() != null && groupService.getById(groupUserVO.getUserId())==null){
            return (CommonResult) result.failCustom(-10086,"不存在该分组");
        }
        if (groupUserService.hadJoined(groupUserVO)){
            return (CommonResult) result.failCustom(-10086,"已加入该分组");
        }
        GroupUser groupUser = new GroupUser();
        BeanUtils.copyProperties(groupUserVO,groupUser);
        groupUser.setUserId(UserUtils.getPrincipal());
        //加入所有以存在任务
        List<AttendanceTask> byGroupId = attendanceTaskService.getByGroupId(groupUserVO.getGroupId());
        for (AttendanceTask attendanceTask : byGroupId){
            attendanceTaskUserService.insert(new AttendanceTaskUserVO(attendanceTask.getId(),UserUtils.getPrincipal(),null));
        }
        if (groupUserService.save(groupUser)){
            return result.success("groupUser",groupUser);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建分组成员连接失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) GroupUserVO groupUserVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        GroupUser groupUser = new GroupUser();
        BeanUtils.copyProperties(groupUserVO,groupUser);
        if (groupUserService.updateById(groupUser)){
            return result.success("groupUser",groupUser);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新分组成员连接失败");
        }
    }

    @GetMapping("/delete/{groupUserId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String groupUserId){
        CommonResult result = new CommonResult().init();
        if (groupUserService.getById(groupUserId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组成员连接不存在");
        }
        if (groupUserService.removeById(groupUserId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除分组成员连接失败");
        }
    }

    @GetMapping("/select/{groupUserId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String groupUserId){
        CommonResult result = new CommonResult().init();
        if (groupUserService.getById(groupUserId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组成员连接不存在");
        }
        return result.success("groupUser",groupUserService.getById(groupUserId));
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody GroupUserPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",groupUserService.findPage(pageVO)).end();
        return result;
    }


    @GetMapping("getUsersByGroupId/{groupId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult getUsersByGroupId (@PathVariable String groupId){
        CommonResult result = new CommonResult().init();
        List<GroupUser> usersByGroup = groupUserService.getUsersByGroupId(groupId);
        List<String> usernames = new ArrayList<>();
        for (GroupUser groupUser : usersByGroup){
            String userId = groupUser.getUserId();
            User user = userService.getById(userId);
            if (user != null){
                usernames.add(user.getName());
            }

        }
        result.success("users",usernames);
        return result;
    }


    @GetMapping("/joined/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult joined(@PathVariable String userId){
        CommonResult result = new CommonResult().init();
        result.success("joined",groupUserService.getGroupJoinedByUserId(userId));
        return result;
    }
}
