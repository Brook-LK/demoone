package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Map<String,Object> getUserList (){
        System.out.println("/ulit");
        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("userList",userService.getUserList());
        return userMap;
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public boolean insertUser(){
        User user = new User();
        user.setName("王五");
        user.setAge(19);
        boolean flag = userService.addUser(user);
        return flag;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public boolean deleteUser(@PathVariable(value = "id")int id){
        User user = new User();
        user.setId(id);
        boolean flag = userService.deleteUser(user);
        return flag;
    }

}
