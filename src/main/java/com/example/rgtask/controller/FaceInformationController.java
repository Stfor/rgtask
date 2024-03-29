package com.example.rgtask.controller;


import com.example.rgtask.pojo.AttendanceTaskUser;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.AttendanceTaskUserService;
import com.example.rgtask.service.FaceInformationService;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.FaceUtils;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.validation.Create;
import com.example.rgtask.vo.FaceInformationPageVO;
import com.example.rgtask.vo.FaceInformationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/face-information")
@CrossOrigin
@Slf4j
@Api(value = "ErrandController", tags = "脸部信息接口")
public class FaceInformationController {
    private FaceInformationService faceInformationService;
    private AttendanceTaskUserService attendanceTaskUserService;
    @Autowired
    private void setFaceInformationService(FaceInformationService faceInformationService){
        this.faceInformationService = faceInformationService;
    }
    @Autowired
    private void setAttendanceTaskUserService(AttendanceTaskUserService attendanceTaskUserService){
        this.attendanceTaskUserService = attendanceTaskUserService;
    }

    private PicturesService picturesService;
    @Autowired
    private void setPicturesService(PicturesService picturesService){
        this.picturesService=picturesService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult insert(@RequestBody @Validated({Create.class}) FaceInformationVO faceinformationVO, BindingResult bindingResult){
        log.info("----------------当前的人脸图片地址是---------------------");
        log.info(faceinformationVO.toString());
        log.info("-----------------------------------------------------");
        CommonResult result = new CommonResult().init();
        //参数验证
        /*log.info(String.valueOf(idleGoods));*/
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (faceInformationService.getCountByUserId(UserUtils.getPrincipal()) > 0){
            return (CommonResult) result.failCustom(400,"已存在人脸识别信息无法继续插入");
        }
        if(faceInformationService.insert(faceinformationVO) > 0){
            return result.success("goods",faceinformationVO);
        }else {
            return (CommonResult) result.failCustom(400,"请求人脸信息失败");
        }
    }

    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Create.class}) FaceInformationVO faceinformationVO,BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if(faceInformationService.updateAllById(faceinformationVO) > 0){
            return result.success("goods",faceinformationVO);
        }else {
            return (CommonResult) result.failCustom(400,"更新闲置物品信息失败");
        }
    }

    @GetMapping("/select/{faceInformationId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String faceInformationId){
        CommonResult result = new CommonResult().init();
        result.success("faceInformation",faceInformationService.getById(faceInformationId));
        return result;
    }

    @DeleteMapping("/delete/{goodsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String faceId){
        CommonResult result = new CommonResult().init();
        if(faceInformationService.getById(faceId) == null){
            return (CommonResult) result.failCustom(400,"人脸信息不存在");
        }
        if(faceInformationService.removeAllById(faceId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(400,"删除失败");
        }
    }



    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody FaceInformationPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",faceInformationService.findPage(pageVO));
        result.end();
        log.info("------------------------------返回");
        return result;
    }
    @PostMapping("compareFace")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult compareFace(@RequestBody FaceInformationVO faceInformationVO) throws Exception {
        log.info("当前的任务id是："+faceInformationVO.getTaskId());
        CommonResult result = new CommonResult().init();
        if (faceInformationService.authenticated(faceInformationVO)){
            AttendanceTaskUser attendanceByUserIdAndTaskId = attendanceTaskUserService.getAttendanceByUserIdAndTaskId(faceInformationVO.getTaskId(), UserUtils.getPrincipal());
            if (attendanceByUserIdAndTaskId == null){
                return (CommonResult) result.failCustom(-10086,"未找到该用户的晚点名任务，点名失败");
            }
            attendanceByUserIdAndTaskId.setStatus("1");
            if (attendanceTaskUserService.updateById(attendanceByUserIdAndTaskId)){
                return result.success("compareFace",true);
            }else {
                return (CommonResult) result.failCustom(-10086,"点名打卡失败");
            }
        }else {
            return (CommonResult) result.failCustom(-10086,"人脸认证失败");
        }
    }

    @PostMapping(value = "/uploadPhoto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult upload(@RequestPart(value = "file") MultipartFile file, HttpServletRequest request) {
        CommonResult result = new CommonResult().init();
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        String path = new String();
        if (System.getProperties().getProperty( "os.name" ).contains("Windows")){
            path ="D:\\src"+ File.separator+"img"+File.separator+time;
        }else if (System.getProperties().getProperty( "os.name" ).contains("Linux")){
            path ="/usr/local"+ File.separator+"img"+File.separator+time;
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){
            String fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                result.success("filePath",path+File.separator+fileName);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            result.failCustom(-10086,"文件格式不支持");
        }
        return result;
    }
}
