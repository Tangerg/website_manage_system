package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.LogRecord;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Website;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tangerg
 * @create 2019-03-30 16:41
 */
@Api("网站api")
@RestController
@RequestMapping("/website")
public class WebsiteController extends BaseController {


    @ApiOperation("查询所有网站基本信息")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query")
    public Msg queryWebsiteAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return websiteService.queryWebsiteAll(pageNum, pageSize);
    }

    @ApiOperation("更新网站信息")
    @UserLoginToken
    @AdminToken
    @LogRecord(operate = "更新网站")
    @PostMapping("/update/{id}")
    public Msg updateWebsite(@PathVariable("id") Integer id, @RequestBody Website website) {
        return websiteService.updateWebsite(id, website);
    }

    @ApiOperation("添加网站信息")
    @UserLoginToken
    @AdminToken
    @LogRecord(operate = "添加网站")
    @PostMapping("/add")
    public Msg addWebsite( @Valid @RequestBody Website website) {
        return websiteService.addWebsite( website);
    }

    @ApiOperation("删除网站信息")
    @UserLoginToken
    @AdminToken
    @LogRecord(operate = "删除网站")
    @PostMapping("/delete/{id}")
    public Msg deleteWebsite( @PathVariable("id") Integer id) {
        return websiteService.deleteWebsite( id);
    }
}
