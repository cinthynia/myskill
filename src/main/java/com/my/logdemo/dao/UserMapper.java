package com.my.logdemo.dao;

import com.my.logdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUserList();
    public int saveUser(User user);
}
