package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.vo.UserPageVO;
import com.example.rgtask.vo.UserVO;

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

    User getUserByLoginName(String loginName);

    int insert(UserVO userVO);

    IPage<User> findPage(Page<User> page, UserPageVO pageVO);

    JwtToken loginByLoginNameAndPassword(String loginName) throws Exception;
}
