package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Count;
import com.suse.netcenter.entity.Log;
import com.suse.netcenter.mapper.CountMapper;
import com.suse.netcenter.mapper.LogMapper;
import com.suse.netcenter.service.InformationService;
import com.suse.netcenter.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class InformationImpl implements InformationService {
    @Autowired
    LogMapper logMapper;
    @Autowired
    CountMapper countMapper;
    @Autowired
    WebsiteImpl websiteImpl;
    @Autowired
    DepartmentImpl departmentImpl;
    @Autowired
    UserImpl userImpl;

    @Override
    public Msg infoAllWebsite() {
        return Msg.success().addData("infoCount", selectLastRecord());
    }

    @Override
    public Msg infoLog(Integer pageNum, Integer pageSize) {
        IPage logPage=selectLogByPage(pageNum,pageSize);
        return Msg.success()
                .addData("pageInfo",new PageUtil().createPageDto(logPage))
                .addData("logList", logPage.getRecords());
    }

    public void addNewLog(Log log){
        log.setLogId(0);
        log.setLogCreateTime(new Date());
        try {
            logMapper.insert(log);
        }catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
    public void addNewRecord() {
        newCountRecord(infoCount());
    }

    public void updateRecord() {
        updateRecord(selectLastRecord());
    }

    private void newCountRecord(Count count) {
        count.setId(0);
        count.setUpdateTime(new Date());
        try {
            countMapper.insert(count);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }


    private IPage<Log> selectLogByPage(Integer pageNum, Integer pageSize){
        Page<Log> page = new Page<>(pageNum, pageSize);
        try {
            return logMapper.selectPage(page, null);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
    private Count selectLastRecord() {
        try {
            return countMapper.selectOne(new QueryWrapper<Count>().orderByDesc("count_id").last("limit 0,1"));
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private void updateRecord(Count count) {
        Count tempCount = infoCount();
        tempCount.setId(count.getId());
        tempCount.setUpdateTime(new Date());
        try {
            countMapper.updateById(tempCount);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private Count infoCount() {

        Count count = new Count();

        count.setWebsite(websiteImpl.countWebsite());
        count.setDepartment(departmentImpl.countDept());
        count.setUser(userImpl.countDept());


        count.setBugSql(websiteImpl.countWebsiteByCondition("website_notice_type", 1));
        count.setBugXss(websiteImpl.countWebsiteByCondition("website_notice_type", 2));
        count.setBugCsrf(websiteImpl.countWebsiteByCondition("website_notice_type", 3));
        count.setBugFileUpload(websiteImpl.countWebsiteByCondition("website_notice_type", 4));
        count.setBugJurisdiction(websiteImpl.countWebsiteByCondition("website_notice_type", 5));
        count.setBugOther(websiteImpl.countWebsiteByCondition("website_notice_type", 6));
        count.setBugCount();

        count.setDbOther(websiteImpl.countWebsiteByCondition("website_db_type", 6));
        count.setDbOracle(websiteImpl.countWebsiteByCondition("website_db_type", 5));
        count.setDbAccess(websiteImpl.countWebsiteByCondition("website_db_type", 3));
        count.setDbMSSql(websiteImpl.countWebsiteByCondition("website_db_type", 4));
        count.setDbMysql(websiteImpl.countWebsiteByCondition("website_db_type", 2));
        count.setDbNo(websiteImpl.countWebsiteByCondition("website_db_type", 1));

        count.setLanHtml(websiteImpl.countWebsiteByCondition("website_lan_type", 1));
        count.setLanPhp(websiteImpl.countWebsiteByCondition("website_lan_type", 2));
        count.setLanAsp(websiteImpl.countWebsiteByCondition("website_lan_type", 3));
        count.setLanJsp(websiteImpl.countWebsiteByCondition("website_lan_type", 4));
        count.setLanOther(websiteImpl.countWebsiteByCondition("website_lan_type", 5));

        return count;
    }

}
