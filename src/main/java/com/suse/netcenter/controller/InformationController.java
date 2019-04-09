package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tangerg
 * @create 2019-03-27 11:01
 */
@Api("信息相关api")
@RestController
@RequestMapping("/info")
public class InformationController extends BaseController {
    /*查看网站信息*/
    @ApiOperation("统计网站语言，漏洞，数据库信息")
    @UserLoginToken
    @AdminToken
    @GetMapping("/website")
    public Msg infoAllWebsite() {
        return informationService.infoAllWebsite();
    }
    /*查看日志信息*/
    @UserLoginToken
    @AdminToken
    @GetMapping("/log")
    public Msg infoLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return informationService.infoLog(pageNum,pageSize);
    }
}
