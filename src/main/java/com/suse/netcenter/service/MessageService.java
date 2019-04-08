package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Message;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-04-04 15:14
 */
@Service
public interface MessageService {

    Msg queryMsg(String condition, Integer pageNum, Integer pageSize, String token);

    Msg sendMsg(Message message, String token);

    Msg deleteMsg(Integer id, String token);

    Msg readMsg(Integer id, String token);
}
