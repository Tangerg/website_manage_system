package com.suse.netcenter;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.*;
import com.suse.netcenter.mapper.*;
import com.suse.netcenter.util.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetcenterApplicationTests {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WebsiteMapper websiteMapper;


    @Test
    public void testSelectApplication() {
        System.out.println(("----- selectAll method test ------"));
        List<Application> List = applicationMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectCount() {
        System.out.println(("----- selectAll method test ------"));
        List<Count> List = countMapper.selectList(null);
        Assert.assertEquals(1, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectDept() {
        System.out.println(("----- selectAll method test ------"));
        List<Department> List = departmentMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectLog() {
        System.out.println(("----- selectAll method test ------"));
        List<Log> List = logMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectMessage() {
        System.out.println(("----- selectAll method test ------"));
        List<Message> List = messageMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectUser() {
        System.out.println(("----- selectAll method test ------"));
        List<User> List = userMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }

    @Test
    public void testSelectWeb() {
        System.out.println(("----- selectAll method test ------"));
        List<Website> List = websiteMapper.selectList(null);
        Assert.assertEquals(0, List.size());
        List.forEach(System.out::println);
    }
    @Test
    public void testMsg(){
        Msg msg =  Msg.fail().addMsg("错误");
        System.out.println(msg.toString());
    }
    @Test
    public void getToken(){
        User user = new User();
        user.setUserId(0001);
        user.setUserRoles(1);
        user.setUserJobNum(0001);
        TokenUtil tu = new TokenUtil();
        String token = tu.createToken(user);
        System.out.println(token);
    }
    @Test
    public void verifyToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiMSIsIjEiLCIxIl0sImlzcyI6IlNpY2h1YW5Vbml2ZXJzaXR5IG9mIFNjaWVuY2UgJiBFbmdpbmVlcmluZyBOZXRDZW50ZXIiLCJleHAiOjE1NTM5MzY4MjgsImlhdCI6MTU1Mzg1MDQyOH0.7Veuz0oQfphgR6gBEgH0fiNowWpZkI9DkgjuxBfoyzs";
        TokenUtil tu = new TokenUtil();
        System.out.println(tu.verifyToken(token).toString());
    }
}
