package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.pojo.User;
import com.example.rgtask.mapper.UserMapper;
import com.example.rgtask.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-10-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByLoginNameAndPassword(String loginName,String password) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);
        User user = userMapper.selectOne(wrapper);
        return user;
    }
}
