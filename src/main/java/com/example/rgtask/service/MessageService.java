package com.example.rgtask.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rgtask.pojo.Message;
import com.example.rgtask.vo.MessageVO;
import com.example.rgtask.vo.MessageVO2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService extends IService<Message> {
    List<MessageVO> findmsg(String sendid,String receiveid);

    Boolean modify(MessageVO2 messageVO2);


}
