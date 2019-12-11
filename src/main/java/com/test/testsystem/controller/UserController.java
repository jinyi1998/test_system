package com.test.testsystem.controller;

import com.test.testsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userLogin")
    public String userLogin(){
        return "user/user_login";
    }

    @RequestMapping("/userRegister")
    public String userRegister(){
        return "user/user_register";
    }

    @RequestMapping("/userQuestion")
    public String userQuestion(){
        return "user/user_question";
    }

}
