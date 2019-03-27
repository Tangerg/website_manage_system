package com.suse.netcenter.controller;

import com.suse.netcenter.dto.Msg;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("部门相关api")
@RestController
@RequestMapping("/dept")
public class DepartmentController extends BaseController {
    @PostMapping("/add")
    public Msg deptAdd(){
        return null;
    }
    @PostMapping("/delete")
    public Msg deptDelete(){
        return null;
    }
    @PostMapping("/update")
    public Msg deptUpdate(){
        return null;
    }
    @PostMapping("/query")
    public Msg deptQuery(){
        return null;
    }

}
