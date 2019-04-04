package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-04-04 15:14
 */
@Service
public interface MessageService {
    Msg queryAll();
}
