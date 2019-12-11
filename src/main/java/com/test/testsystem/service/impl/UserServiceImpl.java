package com.test.testsystem.service.impl;

import com.test.testsystem.dao.UserRepos;
import com.test.testsystem.model.User;
import com.test.testsystem.service.UserService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepos userRepos;


    @Override
    public User getUserById(Integer id) {
        return userRepos.findById(id).get();
    }

    @Override
    public JsonResult saveUser(User user) {

        return JsonResult.success(userRepos.save(user));
    }

    @Override
    public JsonResult deleteUser(Integer id) {
        userRepos.deleteById(id);
        return JsonResult.success(null);
    }

    @Override
    public List<User> getUserList() {
        return userRepos.findAll();
    }
}
