package com.test.testsystem.service.impl;

import com.test.testsystem.dao.AdminRepos;
import com.test.testsystem.model.Admin;
import com.test.testsystem.service.AdminService;
import com.test.testsystem.utils.JsonResult;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepos adminRepos;


    @Override
    public Admin getAdminById(Integer id) {
        return adminRepos.findById(id).get();
    }

    @Override
    public JsonResult saveAdmin(Admin admin) {

        return JsonResult.success(adminRepos.save(admin));
    }

    @Override
    public JsonResult deleteAdmin(Integer id) {
        adminRepos.deleteById(id);
        return JsonResult.success(null);
    }

    @Override
    public List<Admin> getAdminList() {
        return adminRepos.findAll();
    }

    @Override
    public JsonResult adminLogin(String username, String password, HttpSession session){
        //根据用户名密码获取相关管理员
        Admin admin = adminRepos.getAdminByUsernameAndPassword(username,password);
        if (admin == null) {
            return JsonResult.error("登录失败");
        }else{
            session.setAttribute("admin",admin);
            return JsonResult.success();
        }

    }
}
