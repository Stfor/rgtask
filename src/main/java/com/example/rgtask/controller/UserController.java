package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.PageResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.UserPageVO;
import com.example.rgtask.vo.UserVO;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "用户接口")
public class UserController {
    private  RedisTemplate<String,String> redisTemplate;
    private  UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/insert")
    public CommonResult add(@RequestBody @Validated({Create.class}) UserVO userVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        String loginName = userVO.getLoginName();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (userService.getUserByLoginName(loginName) != null){
            return (CommonResult) result.failCustom(-10086,"登录名已存在");
        }
        if (userService.insert(userVO) > 0){
            return result.success("user",userVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建用户失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) UserVO userVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        String userId = userVO.getId();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (userService.getById(userId) == null){
            return (CommonResult) result.failCustom(-10086,"该用户不存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setUpdateDate(LocalDateTime.now());
        if (userService.updateById(user)){
            if (StringUtils.isNotBlank(user.getPhoto())){
                //将头像放入redis
                UserUtils.setUserIntoRedis(user);
            }
            return result.success("user",user);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新用户失败");
        }
    }

    @GetMapping("/delete/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String userId){
        CommonResult result = new CommonResult().init();
        User user = UserUtils.getUser();
        if (userService.getById(userId) == null){
            return (CommonResult) result.failCustom(-10086,"该用户不存在");
        }
        if (!user.getId().equals(userId)){
            return (CommonResult) result.failCustom(-10086,"需要删除的用户非当前用户，无法删除");
        }
        if (userService.removeById(user)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除用户失败");
        }
    }

    @GetMapping("/select/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String userId){
        CommonResult result = new CommonResult().init();
        if (userService.getById(userId) == null){
            return (CommonResult) result.failCustom(-10086,"该用户不存在");
        }
        User user = userService.getById(userId);
        return result.success("user",user);
    }

    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody UserPageVO pageVO,BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        Page<User> page = new Page<User>(pageVO.getPageNo(),pageVO.getPageSize());
        result.success("page",userService.findPage(page, pageVO));
        result.end();
        return result;
    }


    @GetMapping("/aa")
    public void aa(){
        String[] photos = {
                "http://43.142.99.39:8080/pictures/202211/1.jpg",
                "http://43.142.99.39:8080/pictures/202211/2.jpg",
                "http://43.142.99.39:8080/pictures/202211/3.jpg",
                "http://43.142.99.39:8080/pictures/202211/4.jpg",
                "http://43.142.99.39:8080/pictures/202211/5.jpg"
        };
        List<User> users = userService.list();
        for (User user : users){
//            String s = new String();
//            for (int i =0;i<12;i++){
//                Random random = new Random();
//                int i1 = random.nextInt(10);
//                s = s + String.valueOf(i1);
//            }
//            user.setPhone(s);
            Random random = new Random();
            int i = random.nextInt(5);
            user.setPhoto(photos[i]);
            userService.updateById(user);
        }

//        //将所有的头像信息放入redis
//        List<User> users = userService.list();
//        for (User user : users){
//            UserUtils.setUerAvatarToRedis(user);
//        }
    }
}
