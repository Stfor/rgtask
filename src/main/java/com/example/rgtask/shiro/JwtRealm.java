package com.example.rgtask.shiro;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.Exception.TokenException;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.utils.UserUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    //shiro的授权认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //shiro的用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String userId = (String)token.getPrincipal();
        if (StringUtils.isNotBlank(userId)){
            User user = UserUtils.getUserFromRedis(userId);
            if (user == null){
                user = userService.getById(userId);
            }
            if (user == null){
                throw new TokenException(-100086,"不存在该用户");
            }
            return new SimpleAuthenticationInfo(user.getId(),user.getLoginName(), getName());
        }
        return null;
    }
}
