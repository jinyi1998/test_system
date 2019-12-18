package com.test.testsystem.controller;

import com.test.testsystem.model.User;
import com.test.testsystem.service.UserService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userHome")
    public String userHome(){
        return "user_home";
    }


    @RequestMapping("/userLogin")
    public String userLogin(){
        return "user_login";
    }

    @RequestMapping("/doUserLogin")
    @ResponseBody
    public JsonResult doUserLogin(String username, String password, HttpSession session){
        return userService.doUserLogin(username,password,session);
    }

    @RequestMapping("/userSetting")
    public String userSetting(){
        return "user/user_setting";
    }

    @RequestMapping("/userRegister")
    public String userRegister(){
        return "user_register";
    }

    @RequestMapping("/userQuestion")
    public String userQuestion(){
        return "user/user_question";
    }

    @RequestMapping("/userDetail")
    public String userDetail(@RequestParam("id") Integer id, Model model){
        if (null == id || 0 ==id){
            model.addAttribute("user",new User());
        }else {
            model.addAttribute("user",userService.getUserById(id));
        }
        return "user/admin_user_detail";
    }

    @RequestMapping("/userList")
    public String userList(){
        return "user/admin_user_list";
    }

    @ResponseBody
    @RequestMapping(value = "getUserList",method = RequestMethod.POST)
    public JsonResult getUserList(Integer page, Integer pageSize){
        return userService.getPageUserList(page, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "saveUser",method = RequestMethod.POST)
    public JsonResult saveUser(User user){
        return userService.saveUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    public JsonResult deleteUser(Integer id){
        return userService.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "getUser",method = RequestMethod.POST)
    public JsonResult getUser(Integer id){
        return JsonResult.success(userService.getUserById(id));
    }


}
