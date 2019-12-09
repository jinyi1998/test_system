package com.test.testsystem.service.impl;

import com.test.testsystem.dao.UserRepos;
import com.test.testsystem.model.User;
import com.test.testsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepos userRepos;


    @Override
    public void insertUser() {
        userRepos.save(new User());
    }
}
