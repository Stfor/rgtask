package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.AttendanceTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.AttendanceTaskPageVO;
import com.example.rgtask.vo.AttendanceTaskVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-11-27
 */
public interface AttendanceTaskService extends IService<AttendanceTask> {
    Boolean insert(AttendanceTaskVO attendanceTaskVO);

    Boolean updateAllById(AttendanceTaskVO attendanceTaskVO);

    IPage<AttendanceTask> findPage(AttendanceTaskPageVO pageVO);
}
