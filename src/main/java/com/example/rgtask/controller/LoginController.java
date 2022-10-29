package com.example.rgtask.controller;

import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.utils.JwtUtils;
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
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public CommonResult login(String userName,String password){
        CommonResult result = new CommonResult().init();
        Subject subject = SecurityUtils.getSubject();
        //将用户请求参数封装后，直接提交给Shiro处理
        User user = userService.getUserByLoginNameAndPassword(userName, password);
        JwtToken token = new JwtToken(user.getId(),user.getLoginName());
        subject.login(token);

        String tokenReturn = JwtUtils.sign(user.getId(),user.getLoginName(),JwtUtils.SECRET);
        result.success("token",tokenReturn);
        return result;
    }

    @GetMapping("/asd")
    public String asd(){
        return "asd";
    }

    @GetMapping("/aaa")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public String aaa(){
        return "aaa";
    }
}
