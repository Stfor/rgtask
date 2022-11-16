package com.example.rgtask.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.mapper.UniversityMapper;
import com.example.rgtask.pojo.Comments;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.University;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.UniversityPageVO;
import com.example.rgtask.vo.UniversityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xa
 * @since 2022-10-30
 */
@Mapper
public interface UniversityService extends IService<University> {


    IPage<University> findPage(Page<University> page, UniversityPageVO universityVO);
}
