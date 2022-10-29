package com.example.rgtask.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@Slf4j
public class UserUtils {

    private static UserServiceImpl userService;


    public static String getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            String userid = (String) subject.getPrincipal();
            if (StringUtils.isNotBlank(userid)) {
                return userid;
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("getPrincipal: {}", e.getMessage());
            }
        }
        return null;
    }

    public static User getUser() {
        String userid = getPrincipal();
        if (StringUtils.isNotBlank(userid)) {
            User user = userService.getById(userid);
            if (user != null) {
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }
}
