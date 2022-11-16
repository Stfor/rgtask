package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.mapper.ErrandMapper;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.University;
import com.example.rgtask.mapper.UniversityMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.service.UniversityService;
import com.example.rgtask.vo.UniversityPageVO;
import com.example.rgtask.vo.UniversityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xa
 * @since 2022-10-30
 */
@Service
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, University> implements UniversityService {

    private UniversityService universityService;
    private UniversityMapper universityMapper;
    @Autowired
    private void setUniversityService(UniversityService universityService){this.universityService = universityService;}
    @Autowired
    private void setUniversityMapper(UniversityMapper universityMapper){this.universityMapper = universityMapper;}
    @Override
    public IPage<University> findPage(Page<University> page, UniversityPageVO pageVO) {
        //创建查询条件
        QueryWrapper<University> wrapper = new QueryWrapper<>();
        //根据地址查询
        if (pageVO.getLocation()!= null){
            wrapper.eq("location",pageVO.getLocation());
        }
        //根据学校名查询
        if (pageVO.getSchoolName() != null){
            wrapper.eq("school_name",pageVO.getSchoolName());
        }
        //根据学校代码查询
        if (pageVO.getSchoolCode() != null){
            wrapper.eq("school_code",pageVO.getSchoolCode());
        }
        //根据学校所属部门查询
        if (pageVO.getCompetentDarptment() != null){
            wrapper.eq("competent_darptment",pageVO.getCompetentDarptment());
        }
        //根据专or本查询
        if (pageVO.getLevel()!= null){
            wrapper.eq("level",pageVO.getLevel());
        }
        return universityMapper.selectPage(page,wrapper);

    }

}
