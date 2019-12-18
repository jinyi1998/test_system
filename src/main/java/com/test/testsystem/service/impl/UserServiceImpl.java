package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.UserRepos;
import com.test.testsystem.model.User;
import com.test.testsystem.service.UserService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
        if (null == user.getId() || 0 == user.getId()){
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
        }

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

    @Override
    public JsonResult userLogin(String username,String password){
        return JsonResult.success();
    }

    @Override
    public JsonResult getPageUserList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User>users = userRepos.findAll();
        PageInfo pageInfo = new PageInfo(users);
        return JsonResult.success(pageInfo);
    }

    @Override
    public JsonResult doUserLogin(String username, String password, HttpSession session) {
        User user = userRepos.findTopByUsernameAndPassword(username, password);
        if (null != user){
            session.setAttribute("user",user);
            return JsonResult.success();
        }else {
            return JsonResult.error("登录失败");
        }
    }
}
