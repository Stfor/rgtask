package com.example.rgtask.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.utils.ImageUtil;
import com.example.rgtask.utils.JwtUtils;
import com.example.rgtask.utils.MsgCodeUtils;
import com.example.rgtask.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
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
    private UserService userService;
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "登录名", paramType = "query", dataType = "string", required = true, defaultValue = "root"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "string", required = true, defaultValue = "123456"),
    })
    public CommonResult login(String userName,String password) throws Exception {
        CommonResult result = new CommonResult().init();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            result.fail(MsgCodeUtils.MSG_CODE_PARAMETER_IS_NULL);
            return (CommonResult) result.end();
        }

        JwtToken token = userService.loginByLoginNameAndPassword(userName, password);
        result.success("token", token);
        result.success("user", userService.getUserByLoginName(userName));

//        //将用户id，登录名，加密过的密码放入缓存
//        User user = userService.getUserByLoginName(userName);
//        Md5Hash md5Pwd = new Md5Hash(password, user.getSalt(),2);
//        JwtToken token = new JwtToken(userName,md5Pwd.toString());
//        UserUtils.setUserIntoRedis(user);

//        //获取subject进行登录校验
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
//
//        //生成token进行返回
        String tokenReturn = JwtUtils.sign(userService.getUserByLoginName(userName));
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
