package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.AttendanceTask;
import com.example.rgtask.mapper.AttendanceTaskMapper;
import com.example.rgtask.service.AttendanceTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.vo.AttendanceTaskPageVO;
import com.example.rgtask.vo.AttendanceTaskVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
@Service
public class AttendanceTaskServiceImpl extends ServiceImpl<AttendanceTaskMapper, AttendanceTask> implements AttendanceTaskService {
    private AttendanceTaskMapper attendanceTaskMapper;
    @Autowired
    private void setAttendanceTaskMapper(AttendanceTaskMapper attendanceTaskMapper){
        this.attendanceTaskMapper = attendanceTaskMapper;
    }

    @Override
    public Boolean insert(AttendanceTaskVO attendanceTaskVO) {
        AttendanceTask attendanceTask = new AttendanceTask();
        BeanUtils.copyProperties(attendanceTaskVO,attendanceTask);
        attendanceTask.setCreateDate(LocalDateTime.now());
        return attendanceTaskMapper.insert(attendanceTask) > 0;
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

        return attendanceTaskMapper.selectPage(page,wrapper);
    }
}
