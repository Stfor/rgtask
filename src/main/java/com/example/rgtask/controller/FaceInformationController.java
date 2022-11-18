package com.example.rgtask.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}
