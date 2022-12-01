package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.FaceInformationService;
import com.example.rgtask.utils.FaceUtils;
import com.example.rgtask.vo.FaceInformationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@Api(value = "ErrandController", tags = "脸部信息接口")
public class FaceInformationController {
    private FaceInformationService faceInformationService;
    @Autowired
    private void setFaceInformationService(FaceInformationService faceInformationService){
        this.faceInformationService = faceInformationService;
    }
    @PostMapping("compareFace")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult compareFace(@RequestBody FaceInformationVO faceInformationVO) throws Exception {
        CommonResult result = new CommonResult().init();
        if (faceInformationService.authenticated(faceInformationVO)){
            return result.success("compareFace",true);
        }else {
            return (CommonResult) result.failCustom(-10086,"人脸认证失败");
        }
    }
}
