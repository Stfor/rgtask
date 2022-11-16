package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.University;
import com.example.rgtask.service.ErrandService;
import com.example.rgtask.service.UniversityService;
import com.example.rgtask.vo.ErrandPageVO;
import com.example.rgtask.vo.UniversityPageVO;
import com.example.rgtask.vo.UniversityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-30
 */

@RestController
@RequestMapping("/university")
@Api(value = "UniversityController", tags = "大学接口")
public class UniversityController {
    private UniversityService universityService;
    @Autowired
    private void setUniversityService(UniversityService universityService){
        this.universityService = universityService;
    }
    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody UniversityPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        Page<University> page = new Page<University>(pageVO.getPageNo(),pageVO.getPageSize());
        result.success("page",universityService.findPage(page,pageVO));
        result.end();
        return result;
    }
}
