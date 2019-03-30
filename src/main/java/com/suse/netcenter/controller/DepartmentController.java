package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.DepartmentDto;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("部门相关api")
@RestController
@RequestMapping("/dept")
public class DepartmentController extends BaseController {
    //新增部门
    @ApiOperation("新增部门")
    @UserLoginToken
    @AdminToken
    @PostMapping("/add")
    public Msg deptAdd(@RequestBody Department department){
        return null;
    }
    //删除部门
    @ApiOperation("删除部门")
    @UserLoginToken
    @AdminToken
    @PostMapping("/delete/{id}")
    public Msg deptDelete(@PathVariable("id") Long id){
        return null;
    }
    //修改部门
    @ApiOperation("修改部门")
    @UserLoginToken
    @AdminToken
    @PostMapping("/update/{id}")
    public Msg deptUpdate(@PathVariable("id") Long id,@RequestBody Department department){
        return null;
    }

    //查询所有部门
    @ApiOperation("查询所有部门")
    @UserLoginToken
    @GetMapping("/query")
    public Msg deptQueryAll(){
        return null;
    }

    //由部门id查看部门详情
    @ApiOperation("由部门id查看部门详情")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query/{id}")
    public Msg deptQuery(@PathVariable("id") Long id){
        return null;
    }

}
