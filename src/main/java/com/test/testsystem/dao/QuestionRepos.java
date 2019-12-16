package com.test.testsystem.dao;

import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepos extends JpaRepository<Question,Integer> {





    @Query(value = "select  user_id as userId,question_id as questionId,question_name as questionName,user_question_status as userQuestionStatus,knowledge.knowledge_name as knowledgeName " +
            " from question left join user_questions on question.id = user_questions.question_id  left join knowledge on question.knowledge_id = knowledge.id " +
            " where  user_questions.user_id = ?1",nativeQuery = true)
    List<Object []> getUserQuestionKnowledgeList(Integer userId);

}
