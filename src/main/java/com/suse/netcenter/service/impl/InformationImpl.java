package com.suse.netcenter.service.impl;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Count;
import com.suse.netcenter.entity.Log;
import com.suse.netcenter.mapper.CountMapper;
import com.suse.netcenter.mapper.LogMapper;
import com.suse.netcenter.service.InformationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class InformationImpl implements InformationService {
    @Autowired
    LogMapper logMapper;
    @Autowired
    WebsiteImpl websiteImpl;
    @Autowired
    DepartmentImpl departmentImpl;

    @Override
    public Msg infoAllWebsite() {
        return Msg.success().addData("InfoCount",infoCount());
    }

    @Override
    public Msg infoLog() {
        try {
            List<Log> logList = logMapper.selectList(null);
            return Msg.success().addData("info", logList);
        } catch (Exception e) {
            throw new RuntimeException("发生错误");
        }
    }
    private Count infoCount(){
        Integer deptCount = departmentImpl.countDept();
        Integer websiteCount = websiteImpl.countWebsite();
        Integer lanHtml = websiteImpl.countWebsiteByCondition("website_type", 1, false);
        Integer lanPHP = websiteImpl.countWebsiteByCondition("website_type", 2, false);
        Integer lanASP = websiteImpl.countWebsiteByCondition("website_type", 3, false);
        Integer lanJSP = websiteImpl.countWebsiteByCondition("website_type", 4, false);
        Integer lanOther = websiteImpl.countWebsiteByCondition("website_type", 5, true);
        Integer dbNo = websiteImpl.countWebsiteByCondition("website_database", 1, false);
        Integer dbMySql = websiteImpl.countWebsiteByCondition("website_database", 2, false);
        Integer dbAccess = websiteImpl.countWebsiteByCondition("website_database", 3, false);
        Integer dbMsSql = websiteImpl.countWebsiteByCondition("website_database", 4, false);
        Integer dbOracle = websiteImpl.countWebsiteByCondition("website_database", 5, false);
        Integer dbOther = websiteImpl.countWebsiteByCondition("website_database", 6, true);
        Integer bugSql = websiteImpl.countWebsiteByCondition("website_notice_type", 1, false);
        Integer bugXss = websiteImpl.countWebsiteByCondition("website_notice_type", 2, false);
        Integer bugCsrf = websiteImpl.countWebsiteByCondition("website_notice_type", 3, false);
        Integer bugFileUpload = websiteImpl.countWebsiteByCondition("website_notice_type", 4, false);
        Integer bugJurisdiction = websiteImpl.countWebsiteByCondition("website_notice_type", 5, false);
        Integer bugOther = websiteImpl.countWebsiteByCondition("website_notice_type", 6, true);
        Integer bugCount = bugSql + bugXss + bugCsrf + bugFileUpload + bugJurisdiction + bugOther;
        Count count = new Count();
        count.setWebsite(websiteCount);
        count.setDepartment(deptCount);
        count.setBugCount(bugCount);
        count.setBugCsrf(bugCsrf);
        count.setBugFileUpload(bugFileUpload);
        count.setBugJurisdiction(bugJurisdiction);
        count.setBugOther(bugOther);
        count.setBugSql(bugSql);
        count.setBugXss(bugXss);
        count.setDbAccess(dbAccess);
        count.setDbMSSql(dbMsSql);
        count.setDbMysql(dbMySql);
        count.setDbNo(dbNo);
        count.setDbOracle(dbOracle);
        count.setDbOther(dbOther);
        count.setLanAsp(lanASP);
        count.setLanHtml(lanHtml);
        count.setLanJsp(lanJSP);
        count.setLanOther(lanOther);
        count.setLanPhp(lanPHP);
        return count;
    }
}
