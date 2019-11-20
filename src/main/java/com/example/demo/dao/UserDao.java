package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();
//    User getUser(int userId);
    int insertUser(User user);
//    int updateUser(User user);
//    int deleteUser(int userId);
}
