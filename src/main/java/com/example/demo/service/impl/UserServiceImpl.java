package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        if(user.getName() != null && !user.getName().equals("")){
            try{
                int effectedNum = userDao.insertUser(user);
                if(effectedNum>0){
                    return true;
                }else{
                    throw new RuntimeException("插入用户失败！");
                }
            }catch(Exception e){
                throw new RuntimeException("插入用户失败：" + e.getMessage());
            }
        }else{
            throw new RuntimeException("插入用户名不能为空");
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try{
            int effectedNum = userDao.deleteUser(user.getId());
            if(effectedNum > 0){
                return true;
            }else{
                throw new RuntimeException("删除用户失败！");
            }
        }catch(Exception e){
            throw new RuntimeException("删除用户失败：" + e.getMessage());
        }
    }
}
