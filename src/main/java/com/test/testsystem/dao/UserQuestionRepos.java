package com.test.testsystem.dao;

import com.test.testsystem.model.Question;
import com.test.testsystem.model.UserQuestions;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestionRepos  extends JpaRepository<UserQuestions,Integer> {

    List<UserQuestions> findAllByQuestionIdIn (List<Integer> questionIds);
}
