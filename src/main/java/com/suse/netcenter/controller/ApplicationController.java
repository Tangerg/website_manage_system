package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("申请相关api")
@RestController
@RequestMapping("/apply")
public class ApplicationController extends BaseController {

    //存草稿
    @ApiOperation("将申请存入草稿")
    @UserLoginToken
    @PostMapping("/add")
    public Msg ApplicationAdd(@Valid @RequestBody Application application){
        return applicationService.ApplicationAdd(application);
    }
    //正式提交
    @ApiOperation("正式提交申请")
    @UserLoginToken
    @PostMapping("/submit/{id}")
    public Msg ApplicationSubmit(@PathVariable("id") Long id,@RequestBody Application application){
        return null;
    }
    //查询申请
    @ApiOperation("查询申请")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg ApplicationQueryAll(){
        return null;
    }
    //查询单个申请
    @ApiOperation("查询单个申请详情")
    @UserLoginToken
    @GetMapping("/query/{id}")
    public Msg ApplicationQuery(@PathVariable("id") Long id){
        return null;
    }
    //修改申请
    @ApiOperation("修改申请")
    @UserLoginToken
    @PostMapping("/update/{id}")
    public Msg ApplicationUpdate(@PathVariable("id") Long id,@RequestBody Application application){
        return null;
    }
    //审核申请
    @ApiOperation("审核申请")
    @UserLoginToken
    @AdminToken
    @PostMapping("/review/{id}")
    public Msg ApplicationReview(@PathVariable("id") Long id,@RequestBody Application application){
        return null;
    }
}
