package com.suse.netcenter.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.PasswordDto;
import com.suse.netcenter.dto.UserDto;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.mapper.UserMapper;
import com.suse.netcenter.service.UserService;
import com.suse.netcenter.util.PageUtil;
import com.suse.netcenter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:08
 */
@Service
public class UserImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageImpl messageImp;
    @Autowired
    private DepartmentImpl departmentImp;
    @Autowired
    private WebsiteImpl websiteImp;

    /*用户登陆
     *   登陆后返回个人信息
     *   登陆后返回token
     *   登陆后返回未读消息总数
     */
    @Override
    public Msg userLogin(UserDto userDto) {
        User user = selectUserByJobNum(userDto.getUserJobNum());
        if (user == null) {
            return Msg.fail().addMsg("该用户不存在");
        }
        if (!userDto.getUserPassword().equals(user.getUserPassword())) {
            return Msg.fail().addMsg("密码错误");
        }
        Department department = departmentImp.selectDeptById(user.getUserDeptId());
        List websiteList = websiteImp.selectWebsiteByJobNum(userDto.getUserJobNum());
        Integer UnreadMsg = messageImp.selectCountUnreadMsgByJobNum(userDto.getUserJobNum());
        String token = new TokenUtil().createToken(user);
        return Msg.success()
                .addData("user", user)
                .addData("unread", UnreadMsg)
                .addData("dept", department)
                .addData("websiteList", websiteList)
                .addData("token", token);
    }

    /*重新获取用户信息*/
    @Override
    public Msg userInfo(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userJobNum = JWT.decode(token).getAudience().get(1);
        //String userRoles = JWT.decode(token).getAudience().get(3);
        User user = selectUserByJobNum(userJobNum);
        if (user == null || !userId.equals(user.getUserId().toString())) {
            return Msg.fail().addMsg("该用户不存在");
        }
        Department department = departmentImp.selectDeptById(user.getUserDeptId());
        List websiteList = websiteImp.selectWebsiteByJobNum(userJobNum);
        Integer UnreadMsg = messageImp.selectCountUnreadMsgByJobNum(userJobNum);
        return Msg.success().addData("user", user)
                .addData("unread", UnreadMsg)
                .addData("dept", department)
                .addData("websiteList", websiteList);
    }

    /*新增用户
     *  新增基本信息
     */
    @Override
    public Msg userAdd(User user) {
        if (addUser(user)) {
            return Msg.success().addMsg("添加成功");
        }
        return Msg.fail().addMsg("添加失败");
    }

    /*
     * 删除用户
     *   该用户的is_quit设置为1
     */
    @Override
    public Msg userDelete(String JobNum, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        if(userJobNum.equals(JobNum)){
            return Msg.fail().addMsg("不能删除自己");
        }
        User user = selectUserByJobNum(JobNum);
        if (user == null) {
            return Msg.fail().addMsg("该用户不存在");
        }
        if (deleteUserById(user)) {
            return Msg.success().addMsg("删除成功");
        }
        return Msg.fail().addMsg("删除失败");
    }

    /*修改用户
     *  修改基本信息
     *  自己或者管理员修改
     */
    @Override
    public Msg userUpdate(String JobNum, User user, String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        String userJobNum = JWT.decode(token).getAudience().get(1);
        String userRoles = JWT.decode(token).getAudience().get(3);
        if(user.getUserOfficePhone() == null){
            user.setUserOfficePhone("");
        }
        if(user.getUserTel() == null){
            user.setUserTel("");
        }
        //如果传入的参数能够和token中的对应或者权限为管理员
        if ((JobNum.equals(userJobNum) && userId.equals(user.getUserId().toString()) && JobNum.equals(user.getUserJobNum())) || userRoles.equals("1")) {
            if (updateUserByIdAndJobNum(user)) {
                return Msg.success().addMsg("更新成功");
            }
            return Msg.fail().addMsg("更新失败");
        }
        return Msg.fail().addMsg("你没有权限");
    }

    /*
     * 查询所有用户
     *   传入分页条件查询用户基本信息
     *
     */
    @Override
    public Msg userQueryAll(Integer pageNum, Integer pageSize) {
        IPage<User> userIPage = selectUserByPage(pageNum, pageSize);
        return Msg.success()
                .addData("pageInfo", new PageUtil().createPageDto(userIPage))
                .addData("userList", addDeptName(userIPage.getRecords()));
    }

    /*
     * 查询单用户
     *   传入用户工号查询用户基本信息
     *   查询该用户所在部门
     *   查询该用户拥有的网站
     */
    @Override
    public Msg userQuery(String JobNum, String token, Integer flag) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        String userRoles = JWT.decode(token).getAudience().get(3);
        //传入的工号等于token中的工号或者权限为管理员
        if (userJobNum.equals(JobNum) || userRoles.equals("1")) {
            User user = selectUserByJobNum(JobNum);
            if (user == null) {
                return Msg.fail().addMsg("该用户不存在");
            }
            if (flag.equals(0)) {
                return Msg.success()
                        .addData("user", user);
            } else if (flag.equals(1)) {
                Department department = departmentImp.selectDeptById(user.getUserDeptId());
                List websiteList = websiteImp.selectWebsiteByJobNum(userJobNum);
                return Msg.success()
                        .addData("user", user)
                        .addData("dept", department)
                        .addData("websiteList", websiteList);
            }
        }
        return Msg.fail().addMsg("你没有权限");
    }

    //重置用户密码
    @Override
    public Msg userResetPassword(String JobNum) {
        User user = selectUserByJobNum(JobNum);
        if (user == null) {
            return Msg.fail().addMsg("该用户不存在");
        }
        user.setUserPassword(user.getUserJobNum());
        if (updateUserByIdAndJobNum(user)) {
            return Msg.success().addMsg("重置成功");
        }
        return Msg.fail().addMsg("发生错误，重置失败");
    }

    //修改用户密码
    @Override
    public Msg modifyPassword(String JobNum, PasswordDto password, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        if (userJobNum.equals(JobNum)) {
            User user = selectUserByJobNum(JobNum);
            if (user == null) {
                return Msg.fail().addMsg("该用户不存在");
            }
            if (user.getUserPassword().equals(password.getPasswordOld())) {
                user.setUserPassword(password.getPasswordNew());
                if (updateUserByIdAndJobNum(user)) {
                    return Msg.success().addMsg("修改成功");
                }
                return Msg.fail().addMsg("修改失败");
            }
            return Msg.fail().addMsg("原始密码不正确");
        }
        return Msg.fail().addMsg("你没有权限");
    }

    /*由工号查询用户（未离职）*/
    User selectUserByJobNum(String jobNum) {
        try {
            return userMapper.selectOne(new QueryWrapper<User>().eq("user_job_num", jobNum).eq("user_is_quit", 0).orderByDesc("user_id"));
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
    }

    /*由id查询用户*/
    /*User selectUserById(String id) {
        try {
            return userMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
    }*/

    Integer countUserByDept(Integer id) {
        try {
            return userMapper.selectCount(new QueryWrapper<User>()
                    .eq("user_department_id", id)
                    .eq("user_is_quit", 0));
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
    }

    List<User> selectUserListByDept(Integer id) {
        try {
            return userMapper.selectList(new QueryWrapper<User>()
                    .eq("user_department_id", id)
                    .eq("user_is_quit", 0)
                    .orderByDesc("user_id"));
        } catch (Exception e) {
            throw new RuntimeException("用户查询失败");
        }
    }

    boolean updateUserByIdAndJobNum(User user) {
        try {
            return (userMapper.update(user, new UpdateWrapper<User>()
                    .eq("user_id", user.getUserId())
                    .eq("user_job_num", user.getUserJobNum())) != 0);
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
    }

    private boolean deleteUserById(User user) {
        user.setUserIsQuit(1);
        try {
            return (userMapper.updateById(user) != 0);
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
    }

    private IPage<User> selectUserByPage(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        try {
            return userMapper.selectPage(page, new QueryWrapper<User>()
                    .eq("user_is_quit", 0).orderByDesc("user_id"));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    private List<User> addDeptName(List<User> userList) {
        for (User user : userList) {
            Department department = departmentImp.selectDeptById(user.getUserDeptId());
            if (department == null) {
                user.setUserDeptName("部门不存在或未设置");
            } else {
                user.setUserDeptName(department.getDeptName());
            }
        }
        return userList;
    }

    private boolean addUser(User user) {
        user.setUserRoles(0);
        user.setUserId(0);
        user.setUserIsQuit(0);
        user.setUserPassword(user.getUserJobNum());
        try {
            return (userMapper.insert(user) != 0);
        } catch (Exception e) {
            throw new RuntimeException("添加失败");
        }
    }

    Integer countDept() {
        try {
            return userMapper.selectCount(null);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
}
