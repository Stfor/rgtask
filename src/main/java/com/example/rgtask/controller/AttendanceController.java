package com.example.rgtask.controller;

import com.example.rgtask.utils.FaceUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
@Api(value = "AttendanceController", tags = "晚点名接口")
public class AttendanceController {
}
