package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.Pictures;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.validation.Create;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.ErrandVO;
import com.example.rgtask.vo.PartTimeJobVO;
import com.example.rgtask.vo.PicturesVO;
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
 * @since 2022-11-02
 */
@RestController
@RequestMapping("/pictures")
@Api(value = "PicturesController", tags = "图片接口")
public class PicturesController {
    private PicturesService picturesService;
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService = picturesService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody @Validated({Create.class}) PicturesVO picturesVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (picturesService.insert(picturesVO) > 0){
            return result.success("picture",picturesVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"添加图片失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) PicturesVO picturesVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(picturesVO,pictures);
        pictures.setUpdateTime(LocalDateTime.now());
        if (picturesService.updateById(pictures)){
            return result.success("pictures",pictures);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新图片失败");
        }
    }

    @GetMapping("/delete/{pictureId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String pictureId){
        CommonResult result = new CommonResult().init();
        if (picturesService.getById(pictureId) == null){
            return (CommonResult) result.failCustom(-10086,"该图片不存在");
        }
        if (picturesService.removeById(pictureId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除图片失败");
        }
    }

    @GetMapping("/select/{pictureId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String pictureId){
        CommonResult result = new CommonResult().init();
        if (picturesService.getById(pictureId) == null){
            return (CommonResult) result.failCustom(-10086,"该图片不存在");
        }
        Pictures pictures = picturesService.getById(pictureId);
        return result.success("picture",pictures);
    }

    @GetMapping("/findPictures/{areaId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public List<String> findPictures(@PathVariable String areaId){
        return picturesService.findPictures(areaId);
    }

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public List<Pictures> all(){
        return picturesService.list();
    }
}
