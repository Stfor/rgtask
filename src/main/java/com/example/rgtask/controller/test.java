package com.example.rgtask.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rgtask.mapper.IdleGoodsMapper;
import com.example.rgtask.pojo.Errand;
import com.example.rgtask.pojo.IdleGoods;
import com.example.rgtask.pojo.User;
import com.example.rgtask.pojo.Vote;
import com.example.rgtask.service.*;
import com.example.rgtask.vo.PicturesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class test {
    public static void main(String[] args) {
    }
    private UserService userService;
    @Autowired
    private ErrandService errandService;
    private IdleGoodsService idleGoodsService;
    @Autowired
    private IdleGoodsMapper idleGoodsMapper;
    @Autowired
    private PicturesService picturesService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private void setIdleGoodsService(IdleGoodsService idleGoodsService){
        this.idleGoodsService = idleGoodsService;
    }
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/aa")
    public void aa(){
        List<User> list = userService.list();
        String[] grade = {
                "研一",
                "研二",
                "研三"
        };

    }

    @GetMapping("/bb")
    public void bb(){
        QueryWrapper<IdleGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("sponsor_id","8c68f6d5-61f5-4b18-a4fb-d414a16c4a1c");
        List<IdleGoods> idleGoods = idleGoodsMapper.selectList(wrapper);
        for (IdleGoods idleGoods1 : idleGoods){
            picturesService.insert(new PicturesVO(idleGoods1.getId(),"http://43.142.99.39:8080/pictures/202211/3.jpg"));
        }

        List<Vote> list = voteService.list();
        for (Vote vote : list){
            vote.setUserId("8c68f6d5-61f5-4b18-a4fb-d414a16c4a1c");
            voteService.updateById(vote);
        }
    }

}
