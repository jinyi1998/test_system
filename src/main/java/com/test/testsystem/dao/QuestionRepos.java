package com.test.testsystem.dao;

import com.test.testsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepos extends JpaRepository<Question,Integer> {
}
