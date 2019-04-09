package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.DepartmentDto;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Msg deptAdd(@Valid @RequestBody Department department) {
        return departmentService.deptAdd(department);
    }

    //删除部门
    @ApiOperation("删除部门")
    @UserLoginToken
    @AdminToken
    @PostMapping("/delete/{id}")
    public Msg deptDelete(@PathVariable("id") Integer id) {
        return departmentService.deptDelete(id);
    }

    //修改部门
    @ApiOperation("修改部门")
    @UserLoginToken
    @AdminToken
    @PostMapping("/update/{id}")
    public Msg deptUpdate(@PathVariable("id") Integer id,@Valid @RequestBody Department department) {
        return departmentService.deptUpdate(id, department);
    }

    //查询所有部门(分页)
    @ApiOperation("分页查询所有部门")
    @UserLoginToken
    @GetMapping("/query/page")
    public Msg deptQueryByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return departmentService.deptQueryByPage(pageNum, pageSize);
    }

    //查询所有部门
    @ApiOperation("查询所有部门")
    @UserLoginToken
    @GetMapping("/query")
    public Msg deptQueryAll() {
        return departmentService.deptQueryAll();
    }

    //由部门id查看部门详情
    @ApiOperation("由部门id查看部门详情")
    @UserLoginToken
    @AdminToken
    @GetMapping("/query/{id}")
    public Msg deptQuery(@PathVariable("id") Integer id) {
        return departmentService.deptQuery(id);
    }

}
