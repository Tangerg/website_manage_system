package com.suse.netcenter.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Message;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.mapper.MessageMapper;
import com.suse.netcenter.service.MessageService;
import com.suse.netcenter.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-04-04 18:30
 */
@Service
public class MessageImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserImpl userImpl;

    @Override
    public Msg queryMsg(String condition, Integer pageNum, Integer pageSize, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        Integer state = 2;
        /*condition 0：未读 1：已读 2：所有 3：软删除* */
        switch (condition) {
            case "0":
                state = 0;
                break;
            case "1":
                state = 1;
                break;
            case "3":
                state = 3;
                break;
            default:
                break;
        }
        IPage<Message> msgIpage = selectMsgByCondition(state, pageNum, pageSize, userJobNum);
        return Msg.success().addData("pageInfo", new PageUtil().createPageDto(msgIpage))
                .addData("msgList", addSenderName(msgIpage.getRecords()));
    }

    @Override
    public Msg sendMsg(Message message, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        if (userJobNum.equals(message.getMsgSender())) {
            if (addNewMsg(message)) {
                return Msg.success().addMsg("发送成功");
            }
            return Msg.fail().addMsg("发送失败");

        }
        return Msg.fail().addMsg("参数错误");
    }

    @Override
    public Msg trashMsg(Integer id, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        Message message = selectMsgById(id);
        if (message != null) {
            if (userJobNum.equals(message.getMsgReceiver())) {
                if (deleteOrReadMsgById(message, true)) {
                    return Msg.success().addMsg("删除成功");
                }
                return Msg.fail().addMsg("删除失败");
            }
            return Msg.fail().addMsg("你没有权限");
        }
        return Msg.fail().addMsg("该消息记录不存在");
    }

    @Override
    public Msg readMsg(Integer id, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        Message message = selectMsgById(id);
        if (message != null) {
            if (userJobNum.equals(message.getMsgReceiver())) {
                if (deleteOrReadMsgById(message, false)) {
                    return Msg.success().addMsg("操作成功");
                }
                return Msg.fail().addMsg("操作失败");
            }
            return Msg.fail().addMsg("你没有权限");
        }
        return Msg.fail().addMsg("该消息记录不存在");
    }

    @Override
    public Msg deleteMsg(Integer id, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        Message message = selectMsgById(id);
        if (message != null) {
            if (userJobNum.equals(message.getMsgReceiver())) {
                if (deleteMsg(id)) {
                    return Msg.success().addMsg("操作成功");
                }
                return Msg.fail().addMsg("操作失败");
            }
            return Msg.fail().addMsg("你没有权限");
        }
        return Msg.fail().addMsg("该消息记录不存在");
    }

    private Message selectMsgById(Integer id) {
        try {
            return messageMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private boolean deleteMsg(Integer id) {
        return (messageMapper.deleteById(id) != 0);
    }

    //已读，删除公用方法
    private boolean deleteOrReadMsgById(Message msg, boolean flag) {
        //flag true:软删除 false：标记未已读
        if (flag) {
            if (msg.getMsgIsDel().equals(1)) {
                return false;
            }
            msg.setMsgIsDel(1);
        } else {
            if (msg.getMsgState().equals(1)) {
                return false;
            }
            msg.setMsgState(1);
        }
        try {
            return (messageMapper.updateById(msg) != 0);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private boolean addNewMsg(Message message) {
        message.setMsgId(0);
        message.setMsgCreateTime(new Date());
        try {
            return (messageMapper.insert(message) != 0);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
    private List<Message> addSenderName(List<Message> messageList){
        for (Message message : messageList) {
            User user = userImpl.selectUserByJobNum(message.getMsgSender());
            message.setMsgSenderName(user.getUserName());
        }
        return messageList;
    }

    private IPage<Message> selectMsgByCondition(Integer condition, Integer pageNum, Integer pageSize, String userJobNum) {
        Page<Message> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("message_receiver", userJobNum).orderByDesc("message_create_time");
        if (condition == 3) {
            queryWrapper.eq("message_is_del", 1);
        } else {
            queryWrapper.eq("message_is_del", 0);
            if (condition != 2) {
                queryWrapper.eq("message_state", condition);
            }
        }

        try {
            return messageMapper.selectPage(page, queryWrapper);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }

    }

    //查询该员工的未读消息总数
    Integer selectCountUnreadMsgByJobNum(String JobNum) {
        try {
            return messageMapper.selectCount(new QueryWrapper<Message>()
                    .eq("message_receiver", JobNum)
                    .eq("message_state", 0)
                    .eq("message_is_del", 0)
            );
        } catch (Exception e) {
            throw new RuntimeException("消息查询失败");
        }
    }
}
