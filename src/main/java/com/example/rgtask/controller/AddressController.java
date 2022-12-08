package com.example.rgtask.controller;


import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.service.AddressService;
import com.example.rgtask.validation.Update;
import com.example.rgtask.vo.AddressPageVO;
import com.example.rgtask.vo.AddressVO;
import com.example.rgtask.vo.GroupPageVO;
import com.example.rgtask.vo.GroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/address")
@Api(value = "AddressController", tags = "地址接口")
public class AddressController {
    private AddressService addressService;
    @Autowired
    private void setAddressService(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult add(@RequestBody AddressVO addressVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (addressService.insert(addressVO)){
            return result.success("address",addressVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"创建收货地址失败");
        }
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Update.class}) AddressVO addressVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if (addressService.updateAllById(addressVO)){
            return result.success("address",addressVO);
        }else {
            return (CommonResult) result.failCustom(-10086,"更新收货地址失败");
        }
    }

    @GetMapping("/delete/{addressId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String addressId){
        CommonResult result = new CommonResult().init();
        if (addressService.getById(addressId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组不存在");
        }
        if (addressService.removeById(addressId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(-10086,"删除分组失败");
        }
    }

    @GetMapping("/select/{addressId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String addressId){
        CommonResult result = new CommonResult().init();
        if (addressService.getById(addressId) == null){
            return (CommonResult) result.failCustom(-10086,"该分组不存在");
        }
        return result.success("address",addressService.getById(addressId));
    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody AddressPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",addressService.findPage(pageVO)).end();
        return result;
    }
}
