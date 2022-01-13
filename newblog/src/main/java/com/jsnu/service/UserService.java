package com.jsnu.service;

import com.jsnu.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkUser(String username);
}
