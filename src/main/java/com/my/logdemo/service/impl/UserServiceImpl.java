package com.my.logdemo.service.impl;

import com.my.logdemo.dao.UserMapper;
import com.my.logdemo.pojo.User;
import com.my.logdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUserList() {
        return userMapper.getAllUserList();
    }

    @Override
    public int inserUser(User user) {
        return userMapper.saveUser(user);
    }
}
