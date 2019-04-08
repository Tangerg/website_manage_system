package com.suse.netcenter;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suse.netcenter.service.impl.InformationImpl;
import com.suse.netcenter.service.impl.WebsiteImpl;
import com.suse.netcenter.util.TimingTask;
import org.springframework.util.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetcenterApplicationTests {
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    CountMapper countMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    LogMapper logMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WebsiteMapper websiteMapper;
    @Autowired
    WebsiteImpl websiteImpl;
    @Autowired
    InformationImpl informationImpl;

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
        user.setUserId(1);
        user.setUserRoles(1);
        user.setUserJobNum("0001");
        TokenUtil tu = new TokenUtil();
        String token = tu.createToken(user);
        System.out.println(token);
    }
    @Test
    public void verifyToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiMSIsIjEiLCIxIl0sImlzcyI6IlNpQ2h1YW5Vbml2ZXJzaXR5IG9mIFNjaWVuY2UgJiBFbmdpbmVlcmluZyBOZXRDZW50ZXIiLCJleHAiOjE1NTM5NDI1MDksImlhdCI6MTU1Mzg1NjEwOX0.PZMHF6QhWawqPI71eTpSl9QEOaOQB7ACpwi92hi7rFI";
        TokenUtil tu = new TokenUtil();
        System.out.println(tu.verifyToken(token));
    }
    @Test
    public void pageTest(){
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(2, 5);
        IPage<User> userIPage = userMapper.selectPage(page,null);
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }
    @Test
    public void messageTest(){
        messageMapper.selectCount(null);
    }

    @Test
    public void selectByConTest(){
        int count = websiteImpl.countWebsiteByCondition("website_type",2,true);
        System.out.println(count);
    }

    @Test
    public void infoWebsiteTest(){
        Msg count = informationImpl.infoAllWebsite();
        System.out.println(count);
    }
    @Test
    public void taskTest() throws InterruptedException {
        TimingTask timingTask = new TimingTask();
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }


}
