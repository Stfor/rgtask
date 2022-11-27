package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
public interface GroupService extends IService<Organization> {
    Boolean insert(GroupVO groupVO);

    Boolean updateAllById(GroupVO groupVO);

    Boolean removeAllById(String groupId);

    IPage<Organization> findPage(GroupPageVO pageVO);
}
