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
 * @since 2022-10-30
 */

@RestController
@RequestMapping("/university")
@Api(value = "UniversityController", tags = "大学接口")
public class UniversityController {

}
