package com.example.rgtask.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "LoginController", tags = "登录接口")
public class LoginController {

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

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

//    @GetMapping("/wechatLogin")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code", value = "code", paramType = "query", dataType = "string", required = true, defaultValue = "root"),
//    })
//    public CommonResult wechatLogin(String code) throws Exception {
//        CommonResult commonResult = new CommonResult().init();
//        String url = "https://api.weixin.qq.com/sns/jscode2session" +
//                "?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
//        String result = "";
//        BufferedReader in = null;
//
//        try {
//            URL url1 = new URL(url);
//            URLConnection urlConnection = url1.openConnection();
//            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        JSON parse = JSONUtil.parse(result);
//        Integer errcode = parse.getByPath("errcode", Integer.class);
//        if (errcode == null){
//            // 用户唯一标识
//            String openid = parse.getByPath("openid", String.class);
//            // 会话密钥
//            String sessionKey = parse.getByPath("session_key", String.class);
//            // 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
//            String unionid = parse.getByPath("unionid", String.class);
//            // 通过oppenid与session_key计算token
//            String token = JwtUtils.sign(openid, sessionKey);
//
//            User user = userService.getUserByLoginName(openid);
//
//            if (user != null){
//                // 该用户以及注册过了
//                // 更新session_key与token
//                signaturePO.setSessionKey(sessionKey);
//                signaturePO.setToken(token);
//                int update = signatureDAO.update(signaturePO);
//                if (update != 1){
//                    throw new BaseException(500, "更新session_key与token失败");
//                }
//                return commonResult.success("token",tokenReturn);
//            }else {
//                // 该用户未被注册,将该用户的session_key与token添加到数据库
//                SignaturePO po = new SignaturePO();
//                po.setOpenid(openid);
//                po.setSessionKey(sessionKey);
//                po.setToken(token);
//
//                int insert = signatureDAO.insert(po);
//                if (insert != 1){
//                    throw new BaseException(500, "更新session_key与token失败");
//                }
//                return commonResult.success("token",tokenReturn);
//            }
//
//        }else if (errcode == -1){
//            throw new Exception( "系统繁忙，稍候再试");
//        }else if (errcode == 40029){
//            throw new Exception("code无效");
//        }else if (errcode == 45011){
//            throw new Exception("频率限制，每个用户每分钟100次");
//        }else {
//            throw new Exception("服务器异常");
//        }
//        return commonResult;
//    }

    /**
     * 上传图片
     *
     */
    @ApiOperation(value = "图片转换base64")
    @PostMapping(value = {"UploadPicture"})
    public CommonResult UploadPicture(@RequestPart(value = "file") MultipartFile[] file, HttpServletRequest request) throws Exception {
        CommonResult  result = new CommonResult().init();
        List<String> pictures = new ArrayList<>();
        for (MultipartFile multipartFile : file){
            pictures.add(ImageUtil.multipartFileToBASE64(multipartFile));
        }
        if (!pictures.isEmpty()) {
            return result.success("pictures",pictures);
        } else {
            return (CommonResult) result.failCustom(-10086,"获取base64失败");
        }
    }

    @GetMapping("aa")
    public String aa(){
        return secret+"____"+appid;
    }
}
