package com.test.testsystem.service;


import com.test.testsystem.model.Admin;
import com.test.testsystem.utils.JsonResult;

import java.util.List;

public interface AdminService {

   Admin getAdminById(Integer id);

   JsonResult saveAdmin(Admin user);

   JsonResult deleteAdmin(Integer id);

   List<Admin> getAdminList();


}
