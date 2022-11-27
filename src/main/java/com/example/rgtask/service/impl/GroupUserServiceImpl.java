package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.GroupUser;
import com.example.rgtask.mapper.GroupUserMapper;
import com.example.rgtask.service.GroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.vo.GroupUserPageVO;
import com.example.rgtask.vo.GroupUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {
    private GroupUserMapper groupUserMapper;
    @Autowired
    private void setGroupUserMapper(GroupUserMapper groupUserMapper){
        this.groupUserMapper = groupUserMapper;
    }

    @Override
    public Boolean deleteByGroupId(String groupId) {
        QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",groupId);
        return groupUserMapper.delete(wrapper) > 0;
    }



    @Override
    public IPage<GroupUser> findPage(GroupUserPageVO pageVO) {
        IPage<GroupUser> page = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();

        return groupUserMapper.selectPage(page,wrapper);
    }
}
