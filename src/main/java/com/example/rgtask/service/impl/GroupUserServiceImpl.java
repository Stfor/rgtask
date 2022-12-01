package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.GroupUser;
import com.example.rgtask.mapper.GroupUserMapper;
import com.example.rgtask.pojo.Organization;
import com.example.rgtask.service.GroupService;
import com.example.rgtask.service.GroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.vo.GroupUserPageVO;
import com.example.rgtask.vo.GroupUserVO;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private GroupService groupService;
    @Autowired
    private void setGroupUserMapper(GroupUserMapper groupUserMapper){
        this.groupUserMapper = groupUserMapper;
    }
    @Autowired
    private void setGroupService(GroupService groupService){
        this.groupService = groupService;
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

    @Override
    public List<GroupUser> getUsersByGroupId(String groupId) {
        QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",groupId);
        return groupUserMapper.selectList(wrapper);
    }

    @Override
    public Boolean hadJoined(GroupUserVO groupUserVO) {
        QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", UserUtils.getPrincipal());
        wrapper.eq("group_id",groupUserVO.getGroupId());
        return groupUserMapper.selectOne(wrapper) != null;
    }

    @Override
    public List<Organization> getGroupJoinedByUserId(String userId) {
        QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<GroupUser> groupUsers = groupUserMapper.selectList(wrapper);
        List<Organization> organizations = new ArrayList<>();
        for (GroupUser groupUser : groupUsers){
            Organization organization = groupService.getById(groupUser.getGroupId());
            if (organization != null){
                organizations.add(organization);
            }
        }
        return organizations;
    }
}
