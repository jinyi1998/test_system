package com.test.testsystem.service;


import com.test.testsystem.model.User;
import com.test.testsystem.utils.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

   User getUserById(Integer id);

   JsonResult saveUser(User user);

   JsonResult deleteUser(Integer id);

   List<User> getUserList();

   JsonResult userLogin(String username,String password);

   JsonResult getPageUserList(Integer page,Integer pageSize);

   JsonResult doUserLogin(String username, String password, HttpSession session);



}
