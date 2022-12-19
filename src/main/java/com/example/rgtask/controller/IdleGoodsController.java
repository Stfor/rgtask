package com.example.rgtask.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rgtask.pojo.CommonResult;
import com.example.rgtask.pojo.IdleGoods;
import com.example.rgtask.pojo.PartTimeJob;
import com.example.rgtask.service.IdleGoodsService;
import com.example.rgtask.service.PicturesService;
import com.example.rgtask.utils.UserUtils;
import com.example.rgtask.validation.Create;
import com.example.rgtask.vo.IdleGoodsPageVO;
import com.example.rgtask.vo.IdleGoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xa
 * @since 2022-10-31
 */
@RestController
@CrossOrigin
@RequestMapping("/idle-goods")
@Api(value = "IdleGoodsController", tags = "闲置物品接口")
@Slf4j
public class IdleGoodsController {


    private IdleGoodsService idleGoodsService;

    @Autowired
    private void setIdleGoodsService(IdleGoodsService idleGoodsService){
        this.idleGoodsService = idleGoodsService;
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
    public CommonResult insert(@RequestBody @Validated({Create.class}) IdleGoodsVO idleGoodsVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        /*log.info(String.valueOf(idleGoods));*/
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if(idleGoodsService.insert(idleGoodsVO) > 0){
            return result.success("goods",idleGoodsVO);
        }else {
            return (CommonResult) result.failCustom(400,"发布闲置信息失败");
        }
    }

    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult update(@RequestBody @Validated({Create.class}) IdleGoodsVO idleGoodsVO,BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            return (CommonResult) result.failIllegalArgument(bindingResult.getFieldErrors()).end();
        }
        if(idleGoodsService.modify(idleGoodsVO) > 0){
            return result.success("goods",idleGoodsVO);
        }else {
            return (CommonResult) result.failCustom(400,"更新闲置物品信息失败");
        }
    }

    @DeleteMapping("/delete/{goodsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult delete(@PathVariable String goodsId){
        CommonResult result = new CommonResult().init();
        if(idleGoodsService.getById(goodsId) == null){
            return (CommonResult) result.failCustom(400,"该物品不存在");
        }
        if(idleGoodsService.removeAllById(goodsId)){
            return (CommonResult) result.success();
        }else {
            return (CommonResult) result.failCustom(400,"删除失败");
        }
    }

    @GetMapping("/select/{goodsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult select(@PathVariable String goodsId){
        CommonResult result = new CommonResult().init();
        if(idleGoodsService.getById(goodsId) == null){
            return (CommonResult) result.failCustom(400,"该物品不存在");
        }
        IdleGoods idleGoods = idleGoodsService.getById(goodsId);
        IdleGoodsVO vo = new IdleGoodsVO();
        BeanUtils.copyProperties(idleGoods,vo);
        vo.setPictures(picturesService.findPictures(goodsId));
        vo.setAvatar(UserUtils.getUserAvatarFromRedis(vo.getSponsorId()));
        return result.success("idlGoods",idleGoods);
    }


//    @GetMapping("/select/{goodsId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
//    })
//    public CommonResult cancel(@PathVariable String goodsId){
//        CommonResult result = new CommonResult().init();
//        if(idleGoodsService.getById(goodsId) == null){
//            return (CommonResult) result.failCustom(400,"该物品不存在");
//        }
//        IdleGoods idleGoods = idleGoodsService.getById(goodsId);
//        IdleGoodsVO vo = new IdleGoodsVO();
//        BeanUtils.copyProperties(idleGoods,vo);
//        vo.setPictures(picturesService.findPictures(goodsId));
//        return result.success("idlGoods",idleGoods);
//    }


    @PostMapping("findPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult findPage(@RequestBody IdleGoodsPageVO pageVO, BindingResult bindingResult){
        CommonResult result = new CommonResult().init();
        //参数验证
        if (bindingResult.hasErrors()) {
            result.failIllegalArgument(bindingResult.getFieldErrors()).end();
            return result;
        }
        result.success("page",idleGoodsService.findPage(pageVO));
        result.end();
        log.info("------------------------------返回");
        return result;
    }

    @GetMapping("/receive/{idlgoodsId}")
    @ApiOperation(value = "下单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Access-Token", value = "访问token", paramType = "header", dataType = "string", required = true)
    })
    public CommonResult receive(@PathVariable String idlgoodsId){
        CommonResult result = new CommonResult().init();
        IdleGoods goods = idleGoodsService.getById(idlgoodsId);
        if(goods == null){
            return (CommonResult) result.failCustom(400,"该商品不存在");
        }
        if(idleGoodsService.receive(goods)){
            return result.success("idlegoods",goods);
        }else {
            return (CommonResult) result.failCustom(400,"购买失败");
        }
    }


}
