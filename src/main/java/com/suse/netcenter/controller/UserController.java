package com.suse.netcenter.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tangerg
 * @create 2019-03-27 10:57
 */
@Api("用户相关api")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
}
