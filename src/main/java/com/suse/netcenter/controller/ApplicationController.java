package com.suse.netcenter.controller;

import com.suse.netcenter.dto.Msg;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("申请相关api")
@RestController
@RequestMapping("/apply")
public class ApplicationController extends BaseController {
    @PostMapping("/add")
    public Msg ApplicationAdd(){
        return null;
    }
    @PostMapping("/query")
    public Msg ApplicationQuery(){
        return null;
    }
    @PostMapping("/update")
    public Msg ApplicationUpdate(){
        return null;
    }
}
