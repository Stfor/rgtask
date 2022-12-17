package com.example.rgtask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rgtask.pojo.Message;
import com.example.rgtask.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    List<MessageVO> findmsg(@Param("sendid") String sendid,@Param("receiveid") String receiveid);
}
