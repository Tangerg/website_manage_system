package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Message;
import com.suse.netcenter.mapper.NoticeMapper;
import com.suse.netcenter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-04-04 18:30
 */
@Service
public class MessageImpl implements MessageService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Msg queryAll() {
        return null;
    }
    //查询该员工的未读消息总数
    Integer selectCountUnreadMsgByJobNum(String JobNum) {
        Integer count = 0;
        try {
            count = noticeMapper.selectCount(new QueryWrapper<Message>()
                    .eq("message_receiver", JobNum)
                    .eq("message_state", 0)
                    .eq("message_is_del", 0)
            );
        } catch (Exception e) {
            throw new RuntimeException("消息查询失败");
        }
        return count;
    }


}
