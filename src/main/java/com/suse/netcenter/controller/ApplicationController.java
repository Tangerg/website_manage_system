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

    //正式提交
    @ApiOperation("正式提交申请")
    @UserLoginToken
    @PostMapping("/submit")
    public Msg ApplicationSubmit(@Valid @RequestBody Application application, @RequestHeader String token) {
        return applicationService.ApplicationSubmit(application, token);
    }

    //查询申请
    //condition 0所有 1待审核 2审核通过 3审核未通过
    @ApiOperation("查询所有申请")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg ApplicationQueryAll(@RequestParam(value = "condition", defaultValue = "0") String condition,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return applicationService.ApplicationQueryAll(condition, pageNum, pageSize);
    }

    //查询个人所有申请
    @ApiOperation("查询个人所有申请")
    @UserLoginToken
    @GetMapping("/query/{JobNum}")
    public Msg ApplicationQuery(@PathVariable("JobNum") String JobNum,
                                @RequestParam(value = "condition", defaultValue = "0") String condition,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestHeader String token) {
        return applicationService.ApplicationQuery(JobNum, condition, pageNum, pageSize, token);
    }


    //审核申请
    @ApiOperation("审核申请")
    @UserLoginToken
    @AdminToken
    @PostMapping("/review/{id}/{state}")
    public Msg ApplicationReview(@PathVariable("id") Integer id, @PathVariable("state") Integer state) {
        return applicationService.ApplicationReview(id, state);
    }
}
