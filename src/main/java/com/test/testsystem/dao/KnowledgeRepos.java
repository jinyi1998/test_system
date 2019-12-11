package com.test.testsystem.dao;

import com.test.testsystem.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepos extends JpaRepository<Knowledge,Integer> {
}
