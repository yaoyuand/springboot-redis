package com.redis.service;

import com.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void getUser() throws Exception {
        User user=userService.getUser(1);
        System.out.println("查询用户信息为,name:"+user.getName()+",address:"+user.getAddress()+",city:"+user.getCity());
    }

    @Test
    public void insertUser() throws Exception {
        User user=new User();
        user.setName("李四");
        user.setAddress("江苏无锡");
        user.setCity("无锡");
        boolean flg=userService.insertUser(user);
        System.out.println("新增返回值为:"+flg);
    }

    @Test
    public void deleteUser() throws Exception {
        boolean flg=userService.deleteUser(2);
        System.out.println("删除用户返回值为:"+flg);
    }

    @Test
    public void updateUser() throws Exception {
        User user=new User();
        user.setId(1);
        user.setAddress("中国上海");
        boolean flg=userService.updateUser(user);
        System.out.println("修改用户返回值为:"+flg);
    }
    @Test
    public void getUserById() throws Exception{
        User user=userService.getUserById(3);
        System.out.println("查询返回值为.name:"+user.getName()+",address:"+user.getAddress()+".city:"+user.getCity());
    }

}