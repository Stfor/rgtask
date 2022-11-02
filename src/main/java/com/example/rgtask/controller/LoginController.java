package com.example.rgtask.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.utils.ImageUtil;
import com.example.rgtask.utils.JwtUtils;
import com.example.rgtask.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;

@RestController
@RequestMapping("/api")
@Api(value = "LoginController", tags = "登录接口")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "登录名", paramType = "query", dataType = "string", required = true, defaultValue = "root"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "string", required = true, defaultValue = "123456"),
    })
    public CommonResult login(String userName,String password){
        CommonResult result = new CommonResult().init();
        Subject subject = SecurityUtils.getSubject();
        //将用户请求参数封装后，直接提交给Shiro处理
        User user = userService.getUserByLoginName(userName);
        JwtToken token = new JwtToken(userName,password);
        subject.login(token);

        String tokenReturn = JwtUtils.sign(userName,password,JwtUtils.SECRET);
        UserUtils.setUserIntoRedis(user);
        result.success("token",tokenReturn);
        return result;
    }

    /**
     * 上传图片
     *
     */
    @ApiOperation(value = "图片转换base64")
    @PostMapping(value = {"UploadPicture"})
    public CommonResult UploadPicture(@RequestPart(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        CommonResult  result = new CommonResult().init();
        String pictureBase64 = ImageUtil.multipartFileToBASE64(file);
        if (StringUtils.isNotBlank(pictureBase64)) {
            return result.success("picture",pictureBase64);
        } else {
            return (CommonResult) result.failCustom(-10086,"获取base64失败");
        }
    }
}
