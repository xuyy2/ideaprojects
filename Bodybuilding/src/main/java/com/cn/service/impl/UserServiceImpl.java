package com.cn.service.impl;

import javax.annotation.Resource;

import com.cn.service.IUserService;
import org.springframework.stereotype.Service;

import com.cn.dao.IUserDao;
import com.cn.pojo.User;

@Service("userService")
public class UserServiceImpl implements IUserService{

    @Resource
    private IUserDao userDao;
    @Override
    public User getUserById(int id) {
        return userDao.findUserById(id);
    }

}