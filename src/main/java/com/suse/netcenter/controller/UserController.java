package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.PassToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.UserDto;
import com.suse.netcenter.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("用户相关api")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /*用户登录*/
    @ApiOperation("用户登录")
    @PassToken
    @PostMapping("/login")
    public Msg userLogin(@Valid @RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    /*用户查询*/
    @ApiOperation("查询用户基本信息")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg userQueryAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.userQueryAll(pageNum, pageSize);
    }

    /*单个用户查询*/
    @ApiOperation("单用户查询详细信息")
    @UserLoginToken
    @GetMapping("/query/{JobNum}")
    public Msg userQuery(@PathVariable("JobNum") String JobNum, @RequestHeader String token) {
        return userService.userQuery(JobNum, token);
    }

    /*用户修改*/
    @ApiOperation("修改用户信息")
    @UserLoginToken
    @PostMapping("/update/{JobNum}")
    public Msg userUpdate(@PathVariable("JobNum") String JobNum, @RequestBody User user, @RequestHeader String token) {
        return userService.userUpdate(JobNum, user, token);
    }

    /*用户新增*/
    @ApiOperation("新增用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/add")
    public Msg userAdd(@Valid @RequestBody User user) {
        return userService.userAdd(user);
    }

    /*用户删除*/
    @ApiOperation("删除用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/delete/{JobNum}")
    public Msg userDelete(@PathVariable("JobNum") String JobNum) {
        return userService.userDelete(JobNum);
    }
}
