package com.suse.netcenter.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import com.suse.netcenter.entity.Department;
import com.suse.netcenter.entity.Website;
import com.suse.netcenter.mapper.ApplicationMapper;
import com.suse.netcenter.service.ApplicationService;
import com.suse.netcenter.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:09
 */
@Service
public class ApplicationImpl implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private WebsiteImpl websiteImpl;

    @Autowired
    private DepartmentImpl departmentImpl;

    @Override
    public Msg ApplicationQueryAll(String condition, Integer pageNum, Integer pageSize) {
        IPage<Application> applicationIPage = selectApplicationByPage(condition2state(condition), pageNum, pageSize);
        return Msg.success().addData("pageInfo", new PageUtil().createPageDto(applicationIPage))
                .addData("applicationList", addDeptName(applicationIPage.getRecords()));
    }

    @Override
    public Msg ApplicationQuery(String JobNum, String condition, Integer pageNum, Integer pageSize, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        if (JobNum.equals(userJobNum)) {
            IPage<Application> applicationIPage = selectApplicationByJobNum(JobNum, condition2state(condition), pageNum, pageSize);
            return Msg.success().addData("pageInfo", new PageUtil().createPageDto(applicationIPage))
                    .addData("applicationList", addDeptName(applicationIPage.getRecords()));
        }
        return Msg.fail().addMsg("你没有权限");
    }

    @Override
    public Msg ApplicationSubmit(Application application, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(1);
        //验证网站负责人工号
        if (userJobNum.equals(application.getAppWebDirectorNum())) {
            if (submitApplication(application)) {
                return Msg.success().addMsg("提交成功");
            }
            return Msg.fail().addMsg("提交失败");
        }
        return Msg.fail().addMsg("参数错误");
    }


    @Override
    public Msg ApplicationReview(Integer id, Integer state) {
        if (state.equals(30) || state.equals(40)) {
            Application application = selectApplicationById(id);
            if (application != null) {
                if (application.getAppState().equals(20)) {
                    application.setAppState(state);
                    if (updateApplicationById(application)) {
                        //如果申请通过且申请操作为申请网站，则在网站表生成对应申请的网站
                        if (application.getAppState().equals(40) && application.getAppRecOperate().equals(1)) {
                            if (websiteImpl.addWebsiteByApplication(Application2Website(application))) {
                                return Msg.success().addMsg("操作成功");
                            }
                        }
                        return Msg.success().addMsg("操作成功");
                    }
                }
                return Msg.fail().addMsg("该申请已审核");
            }
            return Msg.fail().addMsg("该申请不存在");
        }
        return Msg.fail().addMsg("参数错误");
    }

    private Integer condition2state(String condition) {
        //condition 0所有 1待审核 2审核未通过 3审核通过
        Integer state = 0;
        switch (condition) {
            case "1":
                state = 20;
                break;
            case "2":
                state = 30;
                break;
            case "3":
                state = 40;
                break;
            default:
                break;
        }
        return state;
    }

    private boolean submitApplication(Application application) {
        application.setAppId(0);
        application.setAppState(20);
        application.setAppSubmitTime(new Date());
        try {
            return (applicationMapper.insert(application) != 0);
        } catch (Exception e) {
            throw new RuntimeException("提交失败");
        }
    }

    private IPage<Application> selectApplicationByPage(Integer state, Integer pageNum, Integer pageSize) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper<Application>().orderByDesc("application_id");
        Page<Application> page = new Page<>(pageNum, pageSize);
        if (state != 0) {
            queryWrapper = queryWrapper.eq("application_state", state);
        }
        try {
            return applicationMapper.selectPage(page, queryWrapper);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private List<Application> addDeptName(List<Application> applicationList) {
        for (Application application : applicationList) {
            Department department = departmentImpl.selectDeptById(application.getAppDeptId());
            if (department == null) {
                application.setAppDeptName("部门不存在或未设置");
            } else {
                application.setAppDeptName(department.getDeptName());
            }
        }
        return applicationList;
    }

    private IPage<Application> selectApplicationByJobNum(String JobNum, Integer state, Integer pageNum, Integer pageSize) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper<Application>().eq("application_website_director_num", JobNum).orderByDesc("application_id");
        Page<Application> page = new Page<>(pageNum, pageSize);
        if (state != 0) {
            queryWrapper = queryWrapper.eq("application_state", state);
        }
        try {
            return applicationMapper.selectPage(page, queryWrapper);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private boolean updateApplicationById(Application application) {
        try {
            return (applicationMapper.updateById(application) != 0);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private Application selectApplicationById(Integer id) {
        try {
            return applicationMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private Website Application2Website(Application application) {
        Website website = new Website();
        website.setWebsiteId(0);
        website.setWebsiteDeptId(application.getAppDeptId());
        website.setWebsiteDirectorNum(application.getAppWebDirectorNum());
        website.setWebsiteCreateTime(new Date());
        website.setWebsiteDBType(application.getAppWebsiteDBType());
        website.setWebsiteDBOther(application.getAppWebsiteDBOther());
        website.setWebsiteDomain(application.getAppWebsiteDomain());
        website.setWebsiteInPort(application.getAppWebsiteInPort());
        website.setWebsiteIp(application.getAppWebsiteIp());
        website.setWebsiteIntColumn(application.getAppIntColumn());
        website.setWebsiteName(application.getAppWebsiteName());
        website.setWebsiteOutPort(application.getAppWebsiteOutPort());
        website.setWebsiteServiceContent(application.getAppServiceContent());
        website.setWebsiteSpaceBuilding(application.getAppWebsiteSpaceBuilding());
        website.setWebsiteSpaceCampus(application.getAppWebsiteSpaceCampus());
        website.setWebsiteSpaceRoom(application.getAppWebsiteSpaceRoom());
        website.setWebsiteSpaceType(application.getAppWebsiteSpaceType());
        website.setWebsiteLanType(application.getAppWebsiteLanType());
        website.setWebsiteLanOther(application.getAppWebsiteLanOther());
        website.setWebsiteVisitRange(application.getAppVisitRange());
        website.setWebsiteNoticeType(0);
        website.setWebsiteIsNotice(0);
        website.setWebsiteIsOpen(1);
        website.setWebsiteIsRectify(0);
        website.setWebsiteIsMaster(1);
        return website;
    }
}
