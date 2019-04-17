package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.entity.User;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.WebsiteMapper;
import com.suse.netcenter.service.WebsiteService;
import com.suse.netcenter.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-04-03 16:34
 */
@Service
public class WebsiteImpl implements WebsiteService {

    @Autowired
    WebsiteMapper websiteMapper;

    @Autowired
    UserImpl userImpl;

    @Autowired
    DepartmentImpl departmentImpl;

    @Override
    public Msg queryWebsiteAll(Integer pageNum, Integer pageSize) {
        IPage<Website> websitePage = selectAllWebsite(pageNum, pageSize);
        return Msg.success().addData("pageInfo", new PageUtil().createPageDto(websitePage))
                .addData("websiteList", addDeptNameAndUserName(websitePage.getRecords()));
    }

    @Override
    public Msg updateWebsite(Integer id, Website website) {
        if (id.equals(website.getWebsiteId())) {
            if (updateWebsite(website)) {
                return Msg.success().addMsg("更新成功");
            }
        }
        return Msg.fail().addMsg("更新失败");
    }

    @Override
    public Msg addWebsite(Website website) {
        if (insertWebsite(website)) {
            return Msg.success().addMsg("添加成功");
        }
        return Msg.fail().addMsg("添加失败");
    }

    @Override
    public Msg deleteWebsite(Integer id) {
        if (deleteByWebsite(id)) {
            return Msg.success().addMsg("删除成功");
        }
        return Msg.fail().addMsg("删除失败");
    }


    private IPage<Website> selectAllWebsite(Integer pageNum, Integer pageSize) {
        Page<Website> page = new Page<>(pageNum, pageSize);
        try {
            return websiteMapper.selectPage(page, null);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    private List<Website> addDeptNameAndUserName(List<Website> websiteList) {
        for (Website website : websiteList) {
            User user = userImpl.selectUserByJobNum(website.getWebsiteDirectorNum());
            Department department = departmentImpl.selectDeptById(website.getWebsiteDeptId());
            if (user == null) {
                website.setWebsiteDirectorName("用户不存在或未设置");
            } else {
                website.setWebsiteDirectorName(user.getUserName());
            }
            if (department == null) {
                website.setWebsiteDeptName("部门不存在或未设置");
            } else {
                website.setWebsiteDeptName(department.getDeptName());
            }
        }
        return websiteList;
    }

    private boolean deleteByWebsite(Integer id) {
        try {
            return (websiteMapper.deleteById(id) != 0);
        } catch (Exception e) {
            throw new RuntimeException("删除失败");
        }
    }

    private boolean updateWebsite(Website website) {
        try {
            return (websiteMapper.updateById(website) != 0);
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
    }

    /*private Website selectWebsiteById(Integer id) {
        try {
            return websiteMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }*/

    List selectWebsiteByJobNum(String JobNum) {
        try {
            return websiteMapper.selectList(new QueryWrapper<Website>().eq("website_director_num", JobNum));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    List<Website> selectWebsiteByDept(Integer id) {
        try {
            return websiteMapper.selectList(new QueryWrapper<Website>().eq("website_department_id", id));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    boolean addWebsiteByApplication(Website website) {
        try {
            return (websiteMapper.insert(website) != 0);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private boolean insertWebsite(Website website) {
        website.setWebsiteCreateTime(new Date());
        website.setWebsiteId(0);
        try {
            return (websiteMapper.insert(website) != 0);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    Integer countWebsite() {
        try {
            return websiteMapper.selectCount(null);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    Integer countWebsiteByDept(Integer id) {
        try {
            return websiteMapper.selectCount(new QueryWrapper<Website>().eq("website_department_id", id));
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    //条件统计出网站语言，漏洞，数据库等信息
    public Integer countWebsiteByCondition(String condition, Integer value) {
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(condition, value);
        try {
            return websiteMapper.selectCount(queryWrapper);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
}
