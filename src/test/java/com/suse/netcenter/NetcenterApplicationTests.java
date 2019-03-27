package com.suse.netcenter;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.*;
import com.suse.netcenter.mapper.*;
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
       Msg msg =  Msg.fail().addMsg("错误").addData("user","123");
        System.out.println(msg.toString());
    }
}
