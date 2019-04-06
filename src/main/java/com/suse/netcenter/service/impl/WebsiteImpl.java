package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.dto.PageDto;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.WebsiteMapper;
import com.suse.netcenter.service.WebsiteService;
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
        Page<Website> page = new Page<>(pageNum, pageSize);
        try {
            IPage<Website> websiteIPage = websiteMapper.selectPage(page, null);
            PageDto pageDto = new PageDto();
            pageDto.setCurrent(websiteIPage.getCurrent());
            pageDto.setPages(websiteIPage.getPages());
            pageDto.setSize(websiteIPage.getSize());
            pageDto.setTotal(websiteIPage.getTotal());
            return Msg.success()
                    .addData("pageInfo", pageDto)
                    .addData("websiteList", websiteIPage.getRecords());
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public Msg queryWebsite(Integer id) {
        Website website = new Website();
        try {
            website = websiteMapper.selectById(id);
            if (website == null) {
                return Msg.fail().addMsg("该网站不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
        return Msg.success().addData("website", website);
    }

    @Override
    public Msg updateWebsite(Integer id, Website website) {
        if (id.equals(website.getWebsiteId())) {
            Website website1 = new Website();
            try {
                website1 = websiteMapper.selectById(id);
                if (website1 == null) {
                    return Msg.fail().addMsg("该网站不存在");
                }
                websiteMapper.updateById(website);
                return Msg.success().addMsg("更新成功");
            } catch (Exception e) {
                throw new RuntimeException("更新失败");
            }
        }
        return Msg.fail().addMsg("更新失败");
    }

    List selectWebsiteByJobNum(String JobNum){
        List<Website> website;
        try {
            website=websiteMapper.selectList(new QueryWrapper<Website>().eq("website_director_num",JobNum));
        }catch (Exception e){
            throw new RuntimeException("查询失败");
        }
        return website;
    }
    List<Website> selectWebsiteByDept(Integer id){
        List<Website> website;
        try {
            website=websiteMapper.selectList(new QueryWrapper<Website>().eq("website_department_id",id));
        }catch (Exception e){
            throw new RuntimeException("查询失败");
        }
        return website;
    }

    boolean addWebsiteByApplication(Website website){
        boolean flag= false;
        try{
            if(websiteMapper.insert(website)!=0){
                flag = true;
            }
        }catch (Exception e){
            throw new RuntimeException("操作失败");
        }
        return flag;
    }
}
