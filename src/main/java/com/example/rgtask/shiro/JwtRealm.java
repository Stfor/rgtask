package com.example.rgtask.shiro;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.Exception.TokenException;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    //shiro的授权认证，授权通过Principal查询当前用户的所有权限，然后与要进行访问的资源所需的权限进行比较
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //shiro的用户认证，页面上传来的账号密码和从数据库查询到的账号密码进行比较
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String loginName = (String)token.getPrincipal();
        if (StringUtils.isNotBlank(loginName)){
            User user = UserUtils.getUserFromRedis(loginName);
            if (user == null){
                user = userService.getUserByLoginName(loginName);
            }
            if (user == null){
                throw new TokenException(-100086,"不存在该用户");
            }
            return new SimpleAuthenticationInfo(user.getId(),user.getLoginName(),
                    ByteSource.Util.bytes(user.getSalt()), getName());
        }
        return null;
    }
}
