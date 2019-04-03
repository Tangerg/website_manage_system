package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    public Msg ApplicationSubmit(@PathVariable("id") Integer id,@RequestBody Application application,@RequestHeader String token){
        return applicationService.ApplicationSubmit(id,application,token);
    }
    //查询申请
    @ApiOperation("查询申请")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg ApplicationQueryAll(@RequestParam(value = "condition", defaultValue = "1") String condition){
        return applicationService.ApplicationQueryAll(condition);
    }
    //查询单个申请
    @ApiOperation("查询单个申请详情")
    @UserLoginToken
    @GetMapping("/query/{id}")
    public Msg ApplicationQuery(@PathVariable("id") Integer id,@RequestHeader String token){
        return applicationService.ApplicationQuery(id,token);
    }
    //修改申请
    @ApiOperation("修改申请")
    @UserLoginToken
    @PostMapping("/update/{id}")
    public Msg ApplicationUpdate(@PathVariable("id") Integer id,@RequestBody Application application,@RequestHeader String token){
        return applicationService.ApplicationUpdate(id,application,token);
    }
    //审核申请
    @ApiOperation("审核申请")
    @UserLoginToken
    @AdminToken
    @PostMapping("/review/{id}")
    public Msg ApplicationReview(@PathVariable("id") Integer id,@RequestBody Application application){
        return applicationService.ApplicationReview(id,application);
    }
}
