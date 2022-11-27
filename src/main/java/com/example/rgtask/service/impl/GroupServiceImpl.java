package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Organization;
import com.example.rgtask.mapper.GroupMapper;
import com.example.rgtask.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.GroupUserService;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
@Service
@Transactional
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Organization> implements GroupService {
    private GroupMapper groupMapper;
    private GroupUserService groupUserService;
    @Autowired
    private void setGroupMapper(GroupMapper groupMapper){
        this.groupMapper = groupMapper;
    }
    @Autowired
    private void setGroupUserService(GroupUserService groupUserService){
        this.groupUserService = groupUserService;
    }

    @Override
    public Boolean insert(GroupVO groupVO) {
        Organization group = new Organization();
        BeanUtils.copyProperties(groupVO,group);
        group.setCreateDate(LocalDateTime.now());
        return groupMapper.insert(group) > 0;
    }

    @Override
    public Boolean updateAllById(GroupVO groupVO) {
        Organization group = new Organization();
        BeanUtils.copyProperties(groupVO,group);
        group.setUpdateDate(LocalDateTime.now());
        return groupMapper.updateById(group) > 0;
    }

    @Override
    public Boolean removeAllById(String groupId) {
        if (groupMapper.deleteById(groupId) > 0){
            return groupUserService.deleteByGroupId(groupId);
        }
        return false;
    }

    @Override
    public IPage<Organization> findPage(GroupPageVO pageVO) {
        Page<Organization> page = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        QueryWrapper<Organization> wrapper = new QueryWrapper<>();

        return groupMapper.selectPage(page,wrapper);
    }
}
