package com.suse.netcenter.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.UserDto;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.mapper.UserMapper;
import com.suse.netcenter.service.UserService;
import com.suse.netcenter.util.PageUtil;
import com.suse.netcenter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:08
 */
@Service
public class UserImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    MessageImpl message=new MessageImpl();

    @Override
    public Msg userLogin(UserDto userDto) {
        User user = selectUser(userDto.getUserJobNum());
        if (user == null) {
            return Msg.fail().addMsg("该用户不存在");
        }
        if (!userDto.getUserPassword().equals(user.getUserPassword())) {
            return Msg.fail().addMsg("密码错误");
        }
        Integer UnreadMsg = message.selectCountUnreadMsgByJobNum(userDto.getUserJobNum());
        String token = new TokenUtil().createToken(user);
        return Msg.success()
                .addData("user", user)
                .addData("unread", UnreadMsg)
                .addData("token", token);
    }

    @Override
    public Msg userAdd(User user) {
        user.setUserId(0);
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw new RuntimeException("用户添加失败");
        }
        return Msg.success().addMsg("用户添加成功");
    }

    @Override
    public Msg userDelete(Integer id) {
        User user = new User();
        try {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", id));
            if (user == null || user.getUserIsQuit().equals(1)) {
                return Msg.fail().addMsg("该用户不存在");
            }
            user.setUserIsQuit(1);
            userMapper.updateById(user);
        } catch (Exception e) {
            throw new RuntimeException("删除用户失败");
        }
        return Msg.success().addMsg("删除用户成功");
    }

    @Override
    public Msg userUpdate(Integer id, User user, String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userRoles = JWT.decode(token).getAudience().get(1);
        if (id.equals(user.getUserId()) && (user.getUserId().toString().equals(userId) || userRoles.equals("1"))) {

            try {
                User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", id));
                if (user1 == null || user1.getUserIsQuit().equals(1)) {
                    return Msg.fail().addMsg("该用户不存在");
                }
                if (userMapper.updateById(user) == 0) {
                    return Msg.success().addMsg("用户信息更新失败");
                }
                return Msg.success().addMsg("用户信息更新成功");
            } catch (Exception e) {
                throw new RuntimeException("用户信息更新失败");
            }
        }
        return Msg.fail().addMsg("你没有权限");
    }

    @Override
    public Msg userQueryAll(Integer pageNum, Integer pageSize) {
        IPage userIPage = selectUserByPage(pageNum, pageSize);
        return Msg.success()
                .addData("pageInfo", new PageUtil().createPageDto(userIPage))
                .addData("userList", userIPage.getRecords());
    }

    @Override
    public Msg userQuery(Integer id, String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userRoles = JWT.decode(token).getAudience().get(1);
        if ((id.toString().equals(userId)) || userRoles.equals("1")) {
            try {
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", id));
                if (user == null || user.getUserIsQuit().equals(1)) {
                    return Msg.fail().addMsg("该用户不存在");
                }
                return Msg.success().addData("userInfo", user);
            } catch (Exception e) {
                throw new RuntimeException("查询失败");
            }
        }
        return Msg.fail().addMsg("你没有权限");
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    /*由工号查询用户*/
    User selectUser(String jobNum) {
        User user = null;
        try {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("user_job_num", jobNum).eq("user_is_quit", 0));
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
        return user;
    }

    List selectUserList(List<String> stringList) {
        List userList = new ArrayList();
        try {
            userList = userMapper.selectList(new QueryWrapper<User>().in("user_job_num", stringList));
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
        return userList;
    }

    private IPage selectUserByPage(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> userIPage;
        try {
            userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                    .eq("user_is_quit", 0));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return userIPage;
    }
}
