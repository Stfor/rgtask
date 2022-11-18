package com.example.rgtask.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.Comments;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Location;
import com.example.rgtask.service.LocationService;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.CommentsPageVO;
import com.example.rgtask.vo.CommentsVO;
import com.example.rgtask.vo.LocationPageVO;
import com.example.rgtask.vo.LocationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/location")
@CrossOrigin
@Api(value = "LocationController", tags = "经纬度接口")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody @Validated({Create.class}) LocationVO locationVO, BindingResult bingingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if(bingingResult.hasErrors()){
            return (CommonResult) result.failIllegalArgument(bingingResult.getFieldErrors()).end();
        }
        if(locationService.insert(locationVO) > 0){
            return result.success("location",locationVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"添加位置信息失败");
        }
    }
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token",value = "访问token",paramType = "header",dataType = "string",required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) LocationVO locationVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if(bindingResult.hasErrors()){
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        Location location = new Location();
        BeanUtils.copyProperties(locationVO,location);
        location.setCreateDate(LocalDateTime.now());
        if(locationService.updateById(location)){
            return result.success("location",location);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新位置信息失败");
        }
    }

    @GetMapping("/delete/{locationId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String locationId){
        CommonResult result = new CommonResult().init();
        if (locationService.getById(locationId) == null){
            return (CommonResult) result.failCustom(-10086,"该位置信息不存在");
        }
        if (locationService.removeById(locationId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除位置信息失败");
        }
    }
    @GetMapping("/select/{locationId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String locationId){
        CommonResult result = new CommonResult().init();
        if (locationService.getById(locationId) == null){
            return (CommonResult) result.failCustom(-10086,"该位置信息不存在");
        }
        Location location = locationService.getById(locationId);
        return result.success("location",location);
    }

    @GetMapping("/findComments/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public List<Location> findLocation(@PathVariable String id){
        return locationService.findLocation(id);
    }

    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody LocationPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        Page<Location> page = new Page<Location>(pageVO.getPageNo(),pageVO.getPageSize());
        result.success("page",locationService.findPage(pageVO));
        result.end();
        return result;
    }


}
