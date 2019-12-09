package com.test.testsystem.controller;

import com.test.testsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrameController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(){
        userService.insertUser();
        return "index";
    }
}
