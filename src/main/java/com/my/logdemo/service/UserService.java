package com.my.logdemo.service;

import com.my.logdemo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> getAllUserList();
    public int inserUser(User user);
}
