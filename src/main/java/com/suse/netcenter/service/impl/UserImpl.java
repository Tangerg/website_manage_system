package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.UserDto;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.mapper.UserMapper;
import com.suse.netcenter.service.UserService;
import com.suse.netcenter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:08
 */
@Service
public class UserImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Msg userLogin(UserDto userDto) {
        if (userDto.getUserJobNum() == null) {
            return Msg.fail().addMsg("用户名不能为空");
        }
        if (userDto.getUserPassword() == null || userDto.getUserPassword().trim().isEmpty()) {
            return Msg.fail().addMsg("密码不能为空");
        }
        User user =userMapper.selectOne(new QueryWrapper<User>().eq("user_job_num", userDto.getUserJobNum()));
        if (!userDto.getUserPassword().equals(user.getUserPassword())) {
            return Msg.fail().addMsg("密码错误");
        }
        TokenUtil tokenUtil= new TokenUtil();
        String token = tokenUtil.createToken(user);
        return Msg.success().addData("user",user).addData("token",token);
    }
}
