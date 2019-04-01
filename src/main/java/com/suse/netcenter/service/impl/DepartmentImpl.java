package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.PageDto;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.mapper.DepartmentMapper;
import com.suse.netcenter.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class DepartmentImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Msg deptQueryAll(Integer pageNum, Integer pageSize) {
        Page<Department> page = new Page<>(pageNum, pageSize);
        IPage<Department> deptIPage = departmentMapper.selectPage(page, null);
        PageDto pageDto = new PageDto();
        pageDto.setCurrent(deptIPage.getCurrent());
        pageDto.setPages(deptIPage.getPages());
        pageDto.setSize(deptIPage.getSize());
        pageDto.setTotal(deptIPage.getTotal());
        return Msg.success()
                .addData("pageInfo", pageDto)
                .addData("deptList", deptIPage.getRecords());
    }

    @Override
    public Msg deptQuery(Integer id) {
        Department department = new Department();
        try {
            department = departmentMapper.selectById(id);
            if (department == null) {
                return Msg.fail().addMsg("该部门不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return Msg.success().addData("deptInfo", department);
    }

    @Override
    public Msg deptAdd(Department department) {
        department.setDeptId(0);
        try {
            departmentMapper.insert(department);
        } catch (Exception e) {
            throw new RuntimeException("部门添加失败");
        }
        return Msg.success().addMsg("部门添加成功");
    }

    @Override
    public Msg deptUpdate(Integer id, Department department) {
        if (!id.equals(department.getDeptId())) {
            return Msg.fail().addMsg("参数错误");
        }
        try {
            if (departmentMapper.updateById(department) == 0) {
                return Msg.fail().addMsg("更新失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
        return Msg.success().addMsg("更新成功");
    }

    @Override
    public Msg deptDelete(Integer id) {
        try {
            if (departmentMapper.deleteById(id) == 0) {
                return Msg.fail().addMsg("该部门不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException("删除部门失败");
        }

        return Msg.success().addMsg("删除部门成功");
    }
}
