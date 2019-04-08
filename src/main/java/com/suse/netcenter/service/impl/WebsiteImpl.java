package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.WebsiteMapper;
import com.suse.netcenter.service.WebsiteService;
import com.suse.netcenter.util.PageUtil;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangerg
 * @create 2019-04-03 16:34
 */
@Service
public class WebsiteImpl implements WebsiteService {

    @Autowired
    WebsiteMapper websiteMapper;

    @Override
    public Msg queryWebsiteAll(Integer pageNum, Integer pageSize) {
        IPage<Website> websitePage = selectAllWebsite(pageNum, pageSize);
        return Msg.success().addData("pageInfo", new PageUtil().createPageDto(websitePage))
                .addData("websiteList", websitePage.getRecords());
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


    private IPage<Website> selectAllWebsite(Integer pageNum, Integer pageSize) {
        Page<Website> page = new Page<>(pageNum, pageSize);
        try {
            return websiteMapper.selectPage(page, null);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
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

    Integer countWebsite() {
        try {
            return websiteMapper.selectCount(null);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    //条件统计出网站语言，漏洞，数据库等信息
    public Integer countWebsiteByCondition(String condition, Integer value, boolean flag) {
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        if (!flag) {
            queryWrapper.eq(condition, value);
        } else {
            for (int i = 1; i < value; i++) {
                queryWrapper.ne(condition, i);
            }
        }
        try {
            return websiteMapper.selectCount(queryWrapper);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
}
