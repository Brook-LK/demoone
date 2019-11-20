package com.example.demo.web;

import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
