package com.example.rgtask.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}
