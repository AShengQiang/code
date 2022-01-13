package com.jsnu.service.Impl;

import com.jsnu.dao.UserDao;
import com.jsnu.pojo.User;
import com.jsnu.service.UserService;
import com.jsnu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username) {
        return userDao.findByUsername(username);
    }
}
