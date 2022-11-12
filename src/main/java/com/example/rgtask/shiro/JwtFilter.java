package com.example.rgtask.shiro;

import com.alibaba.fastjson.JSONObject;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.User;
import com.example.rgtask.utils.JwtUtils;
import com.example.rgtask.utils.MsgCodeUtils;
import com.example.rgtask.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends AuthenticatingFilter {

    //由于默认是使用自带的Token生成方法，并且我们使用的是自定义的所以要覆盖在父类的excuse可以看到
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        JwtToken token = new JwtToken((String) servletRequest.getAttribute("userId"),
                (String) servletRequest.getAttribute("userName"));
        return token;
    }

    //在没登录时会调用这个方法，isAccess..是判断用户是否登录，登入则直接返回true否则调用这个方法，可以在prehandler看到
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token = httpServletRequest.getHeader("Access-Token");
//        //判断token是否为空
//        if(StringUtils.isEmpty(token))
//        {
//            throw new TokenException(-100002,"token为空");
//        }
//        String password = JwtUtils.getClaimFiled(token,"password");
//        String username = JwtUtils.getClaimFiled(token,"username");
//        String userId = JwtUtils.getClaimFiled(token,"userId");
//        //验证token是否正确
//        if(!JwtUtils.verify(token,new User(password,username,userId),JwtUtils.SECRET))
//            {
//                //判断token是否过期，且redis中是否存在该token存在则刷新否则重新登录
//                if (JwtUtils.isTokenExpired(token)){
//                    String key = "userId:"+userId;
//                }
//                throw new TokenException(-10014,"token以失效，请重新登录");
//                //throw new ExpiredCredentialsException("token以失效，请重新登录");
//            }
//        servletRequest.setAttribute("username",username);
//        servletRequest.setAttribute("password",password);
//        servletRequest.setAttribute("userId",userId);
//        return executeLogin(servletRequest,servletResponse);
        return false;
    }

    //在登录的时候会进入这个方法如果isAccessAllowed方法返回True，则不会再调用onAccessDenied方法，
    // 如果isAccessAllowed方法返回Flase,则会继续调用onAccessDenied方法。而onAccessDenied方法里面则是具体执行登陆的地方。
    // 由于我们已经登陆，所以此方法就会返回True(filter放行),所以上面的onPreHandle方法里面的onAccessDenied方法就不会被执行

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Access-Token");
        CommonResult result = new CommonResult().init();
        int msgCode = MsgCodeUtils.MSG_CODE_SUCCESS;
        if (StringUtils.isNotBlank(token)){
            String username = JwtUtils.getClaimFiled(token,"userName");
            String userId = JwtUtils.getClaimFiled(token,"userId");
            //验证token是否正确
            if (!JwtUtils.verify(token,new User(userId,username))){
                result.failCustom(-10086,"token错误");
                result.end();
                responseMessage((HttpServletResponse) response, result);
                return false;
            }
            //判断token是否过期，且redis中是否存在该token存在则刷新否则重新登录
            if (JwtUtils.isTokenExpired(token)){
                User userFromRedis = UserUtils.getUserFromRedis(userId);
                if (userFromRedis != null){
                    UserUtils.setUserIntoRedis(userFromRedis);
                }else {
                    result.failCustom(-10086,"token过期请重新登录");
                    result.end();
                    responseMessage((HttpServletResponse) response, result);
                    return false;
                }
            }

            //执行shiro登录
            request.setAttribute("userName",username);
            request.setAttribute("userId",userId);
            try {
                return executeLogin(request,response);
            } catch (Exception e) {
                msgCode = MsgCodeUtils.MSG_CODE_UNKNOWN;
                result.fail(msgCode);
                result.end();
                responseMessage((HttpServletResponse) response, result);
            }

        }else {
            //token为空告知客户端
            msgCode = MsgCodeUtils.MSG_CODE_JWT_TOKEN_ISNULL;
            result.fail(msgCode);
            result.end();
            responseMessage((HttpServletResponse) response, result);
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }


    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, CommonResult result) {
        response.setContentType("application/json; charset=utf-8");
//        response.reset();
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = JSONObject.toJSONString(result);
        out.print(json);
        out.flush();
        out.close();
    }
}
