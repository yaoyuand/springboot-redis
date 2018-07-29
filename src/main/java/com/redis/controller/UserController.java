package com.redis.controller;

import com.redis.entity.User;
import com.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Integer id){
        return userService.getUser(id);
    }
    @RequestMapping("/getUserById")
    @ResponseBody
    public Object getUserById(Integer id){
        return userService.getUserById(id);
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUser(Integer id){
        return userService.deleteUser(id);
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    private boolean updateUser(Integer id,String name,String address,String city){
        User user=new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setCity(city);
        return userService.updateUser(user);
    }
}
