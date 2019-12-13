package com.test.testsystem.controller;

import com.test.testsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "question/admin_login";
    }

    @RequestMapping("/adminDetail")
    public String adminDetail(){
        return "admin/admin_detail";
    }

    @RequestMapping("/adminList")
    public String adminList(){ return "admin/admin_list"; }

    @RequestMapping("/systemList")
    public String systemList(){
        return "management/admin_system_list";
    }
}
