package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.AttendanceTask;
import com.example.rgtask.mapper.AttendanceTaskMapper;
import com.example.rgtask.pojo.AttendanceTaskUser;
import com.example.rgtask.pojo.GroupUser;
import com.example.rgtask.service.AttendanceTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.AttendanceTaskUserService;
import com.example.rgtask.service.GroupService;
import com.example.rgtask.service.GroupUserService;
import com.example.rgtask.vo.AttendanceTaskPageVO;
import com.example.rgtask.vo.AttendanceTaskUserVO;
import com.example.rgtask.vo.AttendanceTaskVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
public class AttendanceTaskServiceImpl extends ServiceImpl<AttendanceTaskMapper, AttendanceTask> implements AttendanceTaskService {
    private AttendanceTaskMapper attendanceTaskMapper;
    private GroupUserService groupUserService;
    private AttendanceTaskUserService attendanceTaskUserService;
    @Autowired
    private void setAttendanceTaskMapper(AttendanceTaskMapper attendanceTaskMapper){
        this.attendanceTaskMapper = attendanceTaskMapper;
    }
    @Autowired
    private void setGroupUserService(GroupUserService groupUserService){
        this.groupUserService = groupUserService;
    }
    @Autowired
    private void setAttendanceTaskUser(AttendanceTaskUserService attendanceTaskUserService){
        this.attendanceTaskUserService = attendanceTaskUserService;
    }
    @Override
    public Boolean insert(AttendanceTaskVO attendanceTaskVO) {
        AttendanceTask attendanceTask = new AttendanceTask();
        BeanUtils.copyProperties(attendanceTaskVO,attendanceTask);
        attendanceTask.setCreateDate(LocalDateTime.now());
        attendanceTask.setId(UUID.randomUUID().toString());
        if (attendanceTaskMapper.insert(attendanceTask) > 0){
            List<GroupUser> usersGroups = groupUserService.getUsersByGroupId(attendanceTask.getGroupId());
            for (GroupUser groupUser : usersGroups){
                attendanceTaskUserService.insert(new AttendanceTaskUserVO(attendanceTask.getId(),groupUser.getUserId(),null));
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateAllById(AttendanceTaskVO attendanceTaskVO) {
        AttendanceTask attendanceTask = new AttendanceTask();
        BeanUtils.copyProperties(attendanceTaskVO,attendanceTask);
        attendanceTask.setUpdateDate(LocalDateTime.now());
        return attendanceTaskMapper.updateById(attendanceTask) > 0;
    }

    @Override
    public IPage<AttendanceTask> findPage(AttendanceTaskPageVO pageVO) {
        Page<AttendanceTask> page = new Page<>(pageVO.getPageNo(),pageVO.getPageSize());
        QueryWrapper<AttendanceTask> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageVO.getGroupId())){
            wrapper.eq("group_id",pageVO.getGroupId());
        }
        return attendanceTaskMapper.selectPage(page,wrapper);
    }

    @Override
    public Boolean deleteAll(String attendanceTaskId) {
        if (attendanceTaskMapper.deleteById(attendanceTaskId) > 0){
            List<AttendanceTaskUser> listByTaskId = attendanceTaskUserService.getListByTaskId(attendanceTaskId);
            for (AttendanceTaskUser attendanceTaskUser : listByTaskId){
                attendanceTaskUserService.removeById(attendanceTaskUser.getId());
            }
            return true;
        }
        return false;
    }


    @Override
    public Boolean deleteAllByGroupId(String groupId) {
        QueryWrapper<AttendanceTask> wrapper = new QueryWrapper<>();
        List<AttendanceTask> listByGroupId = attendanceTaskMapper.selectList(wrapper);
        for (AttendanceTask attendanceTask : listByGroupId){
            if (!deleteAll(attendanceTask.getId())){
                return false;
            }
        }
        return true;
    }
}
