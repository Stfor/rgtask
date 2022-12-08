package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rgtask.pojo.AttendanceTaskUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.AttendanceTaskUserPageVO;
import com.example.rgtask.vo.AttendanceTaskUserReturnVO;
import com.example.rgtask.vo.AttendanceTaskUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-12-01
 */
public interface AttendanceTaskUserService extends IService<AttendanceTaskUser> {
    Boolean insert(AttendanceTaskUserVO attendanceTaskUserVO);

    IPage<AttendanceTaskUserReturnVO> findPage(AttendanceTaskUserPageVO attendanceTaskUserPageVO);

    List<AttendanceTaskUser> getListByTaskId(String taskId);
}
