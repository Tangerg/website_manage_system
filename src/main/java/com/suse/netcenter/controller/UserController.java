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
    public Msg userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    /*用户查询*/
    @ApiOperation("查询用户")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg userQueryAll() {
        return null;
    }

    /*单个用户查询*/
    @ApiOperation("单用户查询")
    @UserLoginToken
    @GetMapping("/query/{id}")
    public Msg userQuery(@PathVariable("id") Long id) {
        return null;
    }

    /*用户修改*/
    @ApiOperation("修改用户信息")
    @UserLoginToken
    @PostMapping("/update/{id}")
    public Msg userUpdate(@PathVariable("id") Long id, @RequestBody User user) {
        return null;
    }

    /*用户新增*/
    @ApiOperation("新增用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/add")
    public Msg userAdd(@RequestBody User user) {
        return null;
    }

    /*用户删除*/
    @ApiOperation("删除用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/delete/{id}")
    public Msg userDelete(@PathVariable("id") Long id) {
        return null;
    }
}
