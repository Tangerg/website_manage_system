package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.UserDto;
import com.suse.netcenter.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:07
 */
@Service
public interface UserService {
    Msg userLogin(UserDto userDto);

    Msg userAdd(User user);

    Msg userDelete(String JobNum);

    Msg userUpdate(String JobNum,User user,String token);

    Msg userQueryAll(Integer pageNum, Integer pageSize);

    Msg userQuery(String JobNum,String token,Integer flag);

    Msg userInfo(String token);
}
