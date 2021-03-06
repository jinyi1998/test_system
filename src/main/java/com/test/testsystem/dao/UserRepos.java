package com.test.testsystem.dao;

import com.test.testsystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos  extends JpaRepository<User,Integer> {

    User findTopByUsernameAndPassword(String username,String password);

    @Override
    Page<User> findAll(Pageable pageable);
}
