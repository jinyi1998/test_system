package com.test.testsystem.dao;

import com.test.testsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepos extends JpaRepository<Admin,Integer> {

    Admin getAdminByUsernameAndPassword(String username,String password);
}
