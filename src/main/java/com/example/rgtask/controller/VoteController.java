package com.example.rgtask.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@Controller
@RequestMapping("/vote")
@Api(value = "VoteController", tags = "投票接口")
public class VoteController {

}
