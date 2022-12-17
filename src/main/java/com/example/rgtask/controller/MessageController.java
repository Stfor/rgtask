package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.Message;
import com.example.rgtask.service.MessageService;
import com.example.rgtask.validation.Create;
import com.example.rgtask.vo.MessageVO;
import com.example.rgtask.vo.MessageVO2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/message")
@Api(tags = "私聊信息接口")
public class MessageController {
    @Autowired
    MessageService messageService;


    @ApiOperation(value = "增加聊天消息")
    @PostMapping("/insert")
    public CommonResult insert(@RequestBody @Validated({Create.class})Message message, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();

        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        message.setCreateTime(LocalDateTime.now());
        message.setId(UUID.randomUUID().toString());
        if(messageService.save(message)){
            return (CommonResult) result.success();
        }
        else return (CommonResult) result.failCustom(400,"失败");
    }

    @ApiOperation(value = "删除聊天消息")
    @DeleteMapping("/delete/{msgid}")
    public CommonResult delete(@PathVariable String msgid){
        CommonResult result = new CommonResult().init();

        if(messageService.removeById(msgid)){
            return (CommonResult) result.success();
        }else return (CommonResult) result.failCustom(400,"该条消息不存在");
    }


    @ApiOperation(value = "修改聊天消息")
    @PutMapping("/modify")
    public CommonResult modify(@RequestBody @Validated({Create.class}) MessageVO2 messageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();

        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }

        if(messageService.modify(messageVO)){
            return (CommonResult) result.success();
        }else return (CommonResult) result.failCustom(400,"修改失败");
    }

    @ApiOperation(value = "查询聊天消息")
    @GetMapping("/findmsg")
    public CommonResult findmsg(String sendid,String receiveid){
        CommonResult result = new CommonResult().init();
        List<MessageVO> list = messageService.findmsg(sendid, receiveid);
        if(list == null){
            return (CommonResult) result.failCustom(400,"不存在聊天消息");
        }
        return (CommonResult) result.success("message",list);
    }

}
