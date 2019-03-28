package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.PassToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("用户相关api")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /*用户登录接口*/
    @PassToken
    @PostMapping("/login")
    public Msg userLogin(){
        return null;
    }
    /*用户查询接口*/
    @UserLoginToken
    @PostMapping("/query")
    public Msg userQuery(){
        return null;
    }
    /*用户更新接口*/
    @UserLoginToken
    @PostMapping("/update")
    public Msg userUpdate(){
        return null;
    }
    /*用户删除接口*/
    @UserLoginToken
    @PostMapping("/delete")
    public Msg userDelete(){
        return null;
    }
}
