package com.example.rgtask.service;

import com.example.rgtask.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-28
 */
public interface UserService extends IService<User> {
    User getUserByLoginNameAndPassword(String loginName,String password);
}
