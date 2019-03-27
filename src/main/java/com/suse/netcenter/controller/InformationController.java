package com.suse.netcenter.controller;

import com.suse.netcenter.dto.Msg;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangerg
 * @create 2019-03-27 11:01
 */
@Api("信息相关api")
@RestController
@RequestMapping("/info")
public class InformationController extends BaseController {
    /*查看网站信息*/
    @GetMapping("/website")
    public Msg infoWebsite(){
        return null;
    }
    /*查看统计信息*/
    @GetMapping("/count")
    public Msg infoCount(){
        return null;
    }
    /*查看日志信息*/
    @GetMapping("/log")
    public Msg infoLog(){
        return null;
    }
}
