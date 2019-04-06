package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.PageDto;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.DepartmentMapper;
import com.suse.netcenter.service.DepartmentService;
import com.suse.netcenter.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class DepartmentImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserImpl userImpl;
    @Autowired
    private WebsiteImpl websiteImpl;

    /*查询所有部门带分页*/
    @Override
    public Msg deptQueryByPage(Integer pageNum, Integer pageSize) {
        IPage deptIPage = selectDeptByPage(pageNum, pageSize);
        return Msg.success()
                .addData("pageInfo", new PageUtil().createPageDto(deptIPage))
                .addData("deptList", deptIPage.getRecords());
    }

    /*查询所有部门*/
    @Override
    public Msg deptQueryAll() {
        return Msg.success().addData("deptList", selectAllDept());
    }


    @Override
    public Msg deptQuery(Integer id) {
        Department department = selectDeptById(id);
        if (department != null) {
            List<User> userList = userImpl.selectUserListByDept(id);
            List<Website> websiteList = websiteImpl.selectWebsiteByDept(id);
            return Msg.success()
                    .addData("deptInfo", department)
                    .addData("userList", userList)
                    .addData("websiteList", websiteList);
        }
        return Msg.fail().addMsg("该部门不存在");
    }

    @Override
    public Msg deptAdd(Department department) {
        if (addDept(department)) {
            return Msg.success().addMsg("部门添加成功");
        }
        return Msg.fail().addMsg("部门添加失败");
    }

    @Override
    public Msg deptUpdate(Integer id, Department department) {
        if (!id.equals(department.getDeptId())) {
            return Msg.fail().addMsg("参数错误");
        }
        if (updateDept(department)) {
            return Msg.success().addMsg("更新成功");
        }
        return Msg.fail().addMsg("更新失败");
    }

    /*删除部门*/
    @Override
    public Msg deptDelete(Integer id) {
        if (deleteDept(id)) {
            List<User> userList = userImpl.selectUserListByDept(id);
            for (User user : userList) {
                user.setUserDeptId(null);
                userImpl.updateUserByIdAndJobNum(user);
            }
            return Msg.success().addMsg("删除部门成功");
        }
        return Msg.fail().addMsg("删除部门失败");
    }

    Department selectDeptById(Integer id) {
        Department department;
        try {
            department = departmentMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return department;
    }

    private boolean addDept(Department department) {
        boolean flag = false;
        department.setDeptId(0);
        try {
            if (departmentMapper.insert(department) != 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("添加失败");
        }
        return flag;
    }

    private boolean updateDept(Department department) {
        boolean flag = false;
        try {
            if (departmentMapper.updateById(department) != 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
        return flag;
    }

    private boolean deleteDept(Integer id) {
        boolean flag = false;
        try {
            if (departmentMapper.deleteById(id) != 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
        return flag;
    }

    private IPage selectDeptByPage(Integer pageNum, Integer pageSize) {
        Page<Department> page = new Page<>(pageNum, pageSize);
        IPage<Department> deptIPage;
        try {
            deptIPage = departmentMapper.selectPage(page, null);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return deptIPage;
    }

    List selectAllDept() {
        List deptList;
        try {
            deptList = departmentMapper.selectList(null);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return deptList;
    }
}