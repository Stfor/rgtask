package com.example.rgtask.shiro;

import com.example.rgtask.Exception.TokenException;
import com.example.rgtask.Exception.TokenOverdue;
import com.example.rgtask.pojo.User;
import com.example.rgtask.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends AuthenticatingFilter {

    //由于默认是使用自带的Token生成方法，并且我们使用的是自定义的所以要覆盖在父类的excuse可以看到
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        JwtToken token = new JwtToken((String) servletRequest.getAttribute("username"),
                (String) servletRequest.getAttribute("userId"));
        return token;
    }

    //在没登录时会调用这个方法，isAccess..是判断用户是否登录，登入则直接返回true否则调用这个方法，可以在prehandler看到
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Access-Token");
        //判断token是否为空
        if(StringUtils.isEmpty(token))
        {
            throw new TokenException(-100002,"token为空");
        }
        String password = JwtUtils.getClaimFiled(token,"password");
        String username = JwtUtils.getClaimFiled(token,"username");
        String userId = JwtUtils.getClaimFiled(token,"userId");
        //验证token是否正确
        if(!JwtUtils.verify(token,new User(password,username,userId),JwtUtils.SECRET))
            {
                //判断token是否过期，且redis中是否存在该token存在则刷新否则重新登录
                if (JwtUtils.isTokenExpired(token)){
                    String key = "userId:"+userId;
                }
                throw new TokenException(-10014,"token以失效，请重新登录");
                //throw new ExpiredCredentialsException("token以失效，请重新登录");
            }
        servletRequest.setAttribute("username",username);
        servletRequest.setAttribute("password",password);
        servletRequest.setAttribute("userId",userId);
        return executeLogin(servletRequest,servletResponse);
    }
}
