package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.AttendanceTaskUser;
import com.example.rgtask.mapper.AttendanceTaskUserMapper;
import com.example.rgtask.service.AttendanceTaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.UserService;
import com.example.rgtask.vo.AttendanceTaskUserPageVO;
import com.example.rgtask.vo.AttendanceTaskUserReturnVO;
import com.example.rgtask.vo.AttendanceTaskUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-12-01
 */
@Service
@Slf4j
public class AttendanceTaskUserServiceImpl extends ServiceImpl<AttendanceTaskUserMapper, AttendanceTaskUser> implements AttendanceTaskUserService {
    private AttendanceTaskUserMapper attendanceTaskUserMapper;
    private UserService userService;
    @Autowired
    private void setAttendanceTaskUserMapper(AttendanceTaskUserMapper attendanceTaskUserMapper){
        this.attendanceTaskUserMapper = attendanceTaskUserMapper;
    }
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public Boolean insert(AttendanceTaskUserVO attendanceTaskUserVO) {
        AttendanceTaskUser attendanceTaskUser = new AttendanceTaskUser();
        BeanUtils.copyProperties(attendanceTaskUserVO,attendanceTaskUser);
        attendanceTaskUser.setCreateTime(LocalDateTime.now());
        return attendanceTaskUserMapper.insert(attendanceTaskUser) > 0;
    }

    @Override
    public IPage<AttendanceTaskUserReturnVO> findPage(AttendanceTaskUserPageVO attendanceTaskUserPageVO) {
        Page<AttendanceTaskUser> page = new Page<>(attendanceTaskUserPageVO.getPageNo(),attendanceTaskUserPageVO.getPageSize());
        QueryWrapper<AttendanceTaskUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(attendanceTaskUserPageVO.getTaskId())){
            wrapper.eq("task_id",attendanceTaskUserPageVO.getTaskId());
        }

        Page<AttendanceTaskUser> page1 = attendanceTaskUserMapper.selectPage(page, wrapper);
        Page<AttendanceTaskUserReturnVO> page2 = new Page<>();
        BeanUtils.copyProperties(page1,page2);
        List<AttendanceTaskUserReturnVO> list = new ArrayList<>();
        for (AttendanceTaskUser taskUser : page1.getRecords()){
            AttendanceTaskUserReturnVO taskUserReturnVO = new AttendanceTaskUserReturnVO();
            BeanUtils.copyProperties(taskUser,taskUserReturnVO);
            taskUserReturnVO.setName(userService.getById(taskUser.getUserId()).getName());
            list.add(taskUserReturnVO);
        }
        page2.setRecords(list);
       return page2;
    }

    @Override
    public List<AttendanceTaskUser> getListByTaskId(String taskId) {
        QueryWrapper<AttendanceTaskUser> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id",taskId);
        return attendanceTaskUserMapper.selectList(wrapper);
    }

    @Override
    public AttendanceTaskUser getAttendanceByUserIdAndTaskId(String taskId, String userId) {
        QueryWrapper<AttendanceTaskUser> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id",taskId);
        wrapper.eq("user_id",userId);
        log.info("----------------");
        log.info(taskId+"----"+userId);
        return attendanceTaskUserMapper.selectOne(wrapper);
    }
}
