package com.example.rgtask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rgtask.mapper.MessageMapper;
import com.example.rgtask.pojo.Message;
import com.example.rgtask.service.MessageService;
import com.example.rgtask.vo.MessageVO;
import com.example.rgtask.vo.MessageVO2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<MessageVO> findmsg(String sendid, String receiveid) {

        return messageMapper.findmsg(sendid,receiveid);
    }

    @Override
    public Boolean modify(MessageVO2 messageVO2) {
        Message message = new Message();
        BeanUtils.copyProperties(messageVO2,message);
        return messageMapper.updateById(message) > 0;
    }
}
