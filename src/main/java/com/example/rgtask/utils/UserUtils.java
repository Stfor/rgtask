package com.example.rgtask.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.rgtask.pojo.User;
import com.example.rgtask.service.UserService;
import com.example.rgtask.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserUtils {

    private static RedisTemplate<String,String> redisTemplate;
    private static UserService userService;
    private static long EXPIRE_TIME = 5*60*1000;
    @Autowired
    public void setUserService(UserService userService) {
        UserUtils.userService = userService;
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        UserUtils.redisTemplate = redisTemplate;
    }
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

    public static User getUserFromRedis(String userId){
        String key = "userId:"+userId;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String loginNameAndPwd = (String) valueOperations.get(key);
        if (loginNameAndPwd == null){
            return null;
        }else {
            String[] split = loginNameAndPwd.split(",");
            User user = new User();
            user.setId(split[0]);
            user.setLoginName(split[1]);
            user.setSalt(split[2]);
            return user;
        }
    }

    public static void setUserIntoRedis(User user){
        String key = "userId:"+user.getId();
        String value = user.getLoginName()+","+user.getPassword()+","+user.getSalt();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,UserUtils.EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }
}
