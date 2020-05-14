package com.test.testsystem.controller;

import com.test.testsystem.model.User;
import com.test.testsystem.model.UserQuestions;
import com.test.testsystem.service.UserService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userHome")
    public String userHome(HttpSession session,Model model){
        User user1 = (User) session.getAttribute("user");
        model.addAttribute("username",user1.getUsername());
        return "user_home";
    }


    @RequestMapping("/")
    public String index(){
        return "user_login";
    }

    @RequestMapping("/userLogin")
    public String userLogin(){
        return "user_login";
    }

    @RequestMapping("/userMain")
    public String userMain(){
        return "user/user_main_menu";
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

    @RequestMapping("/userKnowledge")
    public String userKnowledge(){
        return "user/user_knowledge_list";
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
    @RequestMapping(value = "saveUser",method = RequestMethod.POST,produces = "application/json")
    public JsonResult saveUser(User user,HttpSession session){
        User user1 = (User) session.getAttribute("user");
        user1.setPassword(user.getPassword());
        return userService.saveUser(user1);
    }

    @ResponseBody
    @RequestMapping(value = "adminSaveUser",method = RequestMethod.POST,produces = "application/json")
    public JsonResult adminSaveUser(User user){
        return userService.adminSaveUser(user);
    }

//    @ResponseBody
//    @RequestMapping(value = "saveKnowledge",method = RequestMethod.POST)
//    public JsonResult saveKnowledge(Knowledge knowledge){
//        return knowledgeService.saveKnowledge(knowledge);
//    }


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

    //保存答题记录并退出
    @ResponseBody
    @RequestMapping(value = "saveUserQuestions",method = RequestMethod.POST,produces = "application/json")
    public JsonResult saveUserQuestions(UserQuestions userQuestions,HttpSession session){
        User user = (User) session.getAttribute("user");
        userQuestions.setUserId(user.getId());
        return userService.saveUserQuestions(userQuestions);
    }
}
