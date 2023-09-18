package com.ie.service.admin;

import com.ie.controller.admin.MD5Util;
import com.ie.entity.BUser;
import com.ie.repository.before.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String register(BUser bUser) {
        bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
        if (userRepository.register(bUser) > 0) {
            return "user/login";
        }
        return "user/register";

    }
}
