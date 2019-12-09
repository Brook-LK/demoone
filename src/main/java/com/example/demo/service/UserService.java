package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    boolean addUser(User user);

    boolean deleteUser(User user);
}
