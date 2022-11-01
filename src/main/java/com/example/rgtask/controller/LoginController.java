package com.example.rgtask.controller;

import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.utils.JwtUtils;
import com.example.rgtask.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "LoginController", tags = "登录接口")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public CommonResult login(String userName,String password){
        CommonResult result = new CommonResult().init();
        Subject subject = SecurityUtils.getSubject();
        //将用户请求参数封装后，直接提交给Shiro处理
        User user = userService.getUserByLoginName(userName);
        JwtToken token = new JwtToken(userName,password);
        subject.login(token);

        String tokenReturn = JwtUtils.sign(user.getLoginName(),user.getPassword(),JwtUtils.SECRET);
        UserUtils.setUserIntoRedis(user);
        result.success("token",tokenReturn);
        return result;
    }
}
