package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.GroupUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.pojo.Organization;
import com.example.rgtask.vo.GroupUserPageVO;
import com.example.rgtask.vo.GroupUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
public interface GroupUserService extends IService<GroupUser> {
    Boolean deleteByGroupId(String groupId);

    IPage<GroupUser> findPage(GroupUserPageVO pageVO);

    List<GroupUser> getUsersByGroupId(String groupId);

    Boolean hadJoined(GroupUserVO groupUserVO);

    List<Organization> getGroupJoinedByUserId(String userId);
}
