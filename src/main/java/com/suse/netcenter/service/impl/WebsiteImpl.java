package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.PageDto;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.WebsiteMapper;
import com.suse.netcenter.service.WebsiteService;
import com.suse.netcenter.util.PageUtil;
import io.swagger.models.auth.In;
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
            if(updateWebsite(website)){
                return Msg.success().addMsg("更新成功");
            }
        }
        return Msg.fail().addMsg("更新失败");
    }


    private IPage<Website> selectAllWebsite(Integer pageNum, Integer pageSize) {
        Page<Website> page = new Page<>(pageNum, pageSize);
        IPage<Website> websiteIPage;
        try {
            websiteIPage = websiteMapper.selectPage(page, null);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return websiteIPage;
    }

    private boolean updateWebsite(Website website) {
        boolean flag = false;
        try {
            if (websiteMapper.updateById(website) != 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
        return flag;
    }

    private Website selectWebsiteById(Integer id) {
        Website website;
        try {
            website = websiteMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return website;
    }

    List selectWebsiteByJobNum(String JobNum) {
        List<Website> websiteList;
        try {
            websiteList = websiteMapper.selectList(new QueryWrapper<Website>().eq("website_director_num", JobNum));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return websiteList;
    }

    List<Website> selectWebsiteByDept(Integer id) {
        List<Website> websiteList;
        try {
            websiteList = websiteMapper.selectList(new QueryWrapper<Website>().eq("website_department_id", id));
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return websiteList;
    }

    boolean addWebsiteByApplication(Website website) {
        boolean flag = false;
        try {
            if (websiteMapper.insert(website) != 0) {
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
        return flag;
    }
    Integer countWebsite(){
        Integer count = 0;
        try {
            count = websiteMapper.selectCount(null);
        }catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
        return count;
    }

    //条件统计出网站语言，漏洞，数据库等信息
    public Integer countWebsiteByCondition(String condition,Integer value,boolean flag){
        Integer count = 0;
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        if(!flag){
            queryWrapper.eq(condition,value);
        }else{
            for (int i = 1; i < value; i++) {
                queryWrapper.ne(condition,i);
            }
        }
        try {
            count = websiteMapper.selectCount(queryWrapper);
        }catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
        return count;
    }
}
