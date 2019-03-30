package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:07
 */
@Service
public interface UserService {
    Msg userLogin(UserDto userDto);
}
