package com.suse.netcenter.service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:07
 */

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    Msg deptQueryByPage(Integer pageNum, Integer pageSize);
    Msg deptQueryAll();
    Msg deptQuery(Integer id);
    Msg deptAdd(Department department);
    Msg deptUpdate(Integer id,Department department);
    Msg deptDelete(Integer id);
}
