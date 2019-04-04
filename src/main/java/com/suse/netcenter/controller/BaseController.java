package com.suse.netcenter.controller;

import com.suse.netcenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tangerg
 * @create 2019-03-26 9:48
 */
public class BaseController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    InformationService informationService;
    @Autowired
    UserService userService;
    @Autowired
    WebsiteService websiteService;
    @Autowired
    MessageService messageService;
}
