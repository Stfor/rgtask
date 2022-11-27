package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.GroupUser;
import com.example.rgtask.service.GroupUserService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupUserPageVO;
import com.example.rgtask.vo.GroupUserVO;
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
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/group-user")
@CrossOrigin
@Api(value = "GroupUserController", tags = "分组成员接口")
public class GroupUserController {
    private GroupUserService groupUserService;
    @Autowired
    private void setGroupUserService(GroupUserService groupUserService){
        this.groupUserService = groupUserService;
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
        GroupUser groupUser = new GroupUser();
        BeanUtils.copyProperties(groupUserVO,groupUser);
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

}
