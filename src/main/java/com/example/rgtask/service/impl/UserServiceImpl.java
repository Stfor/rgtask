package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.User;
import com.example.rgtask.mapper.UserMapper;
import com.example.rgtask.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.shiro.JwtToken;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.UserPageVO;
import com.example.rgtask.vo.UserVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UserMapper userMapper;
    private RedisTemplate<String,String>  redisTemplate;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User getUserByLoginNameAndPassword(String loginName,String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",loginName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int insert(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setLoginFlag("1");
        user.setPassword("123456");
        user.setCreateDate(LocalDateTime.now());
        user.setCredit("80");
        user.setExperienceValue("0");
        user.setSalt(UUID.randomUUID().toString());
        user.setId(UUID.randomUUID().toString());
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getSalt(),2);
        user.setPassword(md5Hash.toString());
        user.setPhoto("http://43.142.99.39:8080/pictures/202211/2.jpg");
        //将头像放入redis
        UserUtils.setUserIntoRedis(user);
        if (super.save(user)){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public IPage<User> findPage(Page<User> page, UserPageVO pageVO) {
        //创建查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //根据登录名条件查询
        if (pageVO.getLoginName() != null){
            wrapper.like("login_name",pageVO.getLoginName());
        }
        //根据姓名条件查询
        if (pageVO.getName() != null){
            wrapper.like("name",pageVO.getName());
        }
        //根据年龄条件查询
        if (pageVO.getAge() != null){
            wrapper.eq("age",pageVO.getAge());
        }
        //根据地址条件查询
        if (pageVO.getAddress() != null){
            wrapper.like("address",pageVO.getAddress());
        }
        //根据性别条件查询
        if (pageVO.getSex() != null){
            wrapper.eq("sex",pageVO.getSex());
        }
        //根据邮箱条件查询
        if (pageVO.getEmail() != null){
            wrapper.like("email",pageVO.getEmail());
        }
        //根据经验值条件查询
        if (pageVO.getExperienceValue() != null){
            wrapper.eq("experience_value",pageVO.getExperienceValue());
        }
        //根据信用值条件查询
        if (pageVO.getCredit() != null){
            wrapper.eq("credit",pageVO.getCredit());
        }
        //根据电话号码条件查询
        if (pageVO.getPhone() != null){
            wrapper.eq("photo",pageVO.getPhone());
        }
        //根据登录日期条件查询
        if (pageVO.getLoginDate() != null){
            //待完善
        }
        //根据是否允许登录条件查询
        if (pageVO.getLoginFlag() != null){
            wrapper.eq("login_flag",pageVO.getLoginFlag());
        }
        //根据创建日期条件查询
        if (pageVO.getCreateDate() != null){
            //待完善
        }
        //根据更新日期条件查询
        if (pageVO.getUpdateDate() != null){
            //待完善
        }
        //根据删除标记条件查询
        if (pageVO.getDelFlag() != null){
            wrapper.eq("del_flag",pageVO.getDelFlag());
        }
        return userMapper.selectPage(page , wrapper);
    }

    @Override
    public JwtToken loginByLoginNameAndPassword(String loginName) throws Exception {
        //将用户id，登录名，加密过的密码放入缓存
        User user = getUserByLoginName(loginName);
        if (user == null){
            throw new Exception("不存在该用户");
        }
//        password = new Md5Hash(password, user.getSalt(),2).toString();
//        if (!password.equals(user.getPassword())){
//            throw new Exception("用户名密码错误");
//        }

        //将用户数据放入redis
        UserUtils.setUserIntoRedis(user);
        return new JwtToken(user.getId(),user.getLoginName());
    }
}
