package com.test.testsystem.dao;

import com.test.testsystem.model.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepos extends JpaRepository<Knowledge,Integer> {
    @Override
    Page<Knowledge> findAll(Pageable pageable);
}
