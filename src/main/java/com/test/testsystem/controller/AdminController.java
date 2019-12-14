package com.test.testsystem.controller;

import com.test.testsystem.service.AdminService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "admin_login";
    }

    @RequestMapping("/systemList")
    public String systemList(){
        return "admin/admin_system_list";
    }

    @RequestMapping("/doAdminLogin")
    @ResponseBody
    public JsonResult doAdminLogin(String username, String password, HttpSession session){
        return adminService.adminLogin(username,password,session);

    }
}
