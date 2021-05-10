package com.test.testsystem.dao;

import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepos extends JpaRepository<Question,Integer> {


    @Override
    Page<Question> findAll(Pageable pageable);

    @Query(value = "select  user_id as userId,question_id as questionId,question_name as questionName,user_question_status as userQuestionStatus,knowledge.knowledge_name as knowledgeName,knowledge.id as knowledgeId " +
            " from question left join user_questions on question.id = user_questions.question_id  left join knowledge on question.knowledge_id = knowledge.id " +
            " where  user_questions.user_id = ?1",nativeQuery = true)
    List<Object []> getUserQuestionKnowledgeList(Integer userId);

    @Query(value ="select userId,knowledgeName,knowledgeId,count(knowledgeId) from"+"("+
            "("+"select  user_id as userId,question_id as questionId,question_name as questionName," +
            "user_question_status as userQuestionStatus,knowledge.knowledge_name as knowledgeName,knowledge.id as knowledgeId " +
            " from question left join user_questions on question.id = user_questions.question_id " +
            " left join knowledge on question.knowledge_id = knowledge.id " +
            " where  user_questions.user_id = ?1"+")"+"as userTable"+")"+
            "GROUP BY knowledgeId",nativeQuery = true)
    List<Object []> getCountedUserQuestionKnowledgeList(Integer userId);


    @Query(value = "select knowledge.id as knowledgeId, knowledge.knowledge_name as knowledegeName, question.id as questionId, count(question.id) as questionCount FROM knowledge left join question on knowledge.id = question.knowledge_id " +

            " GROUP BY knowledgeId ",nativeQuery = true)
    List<Object []> getKnowledgeQuestionCount( );




    @Query(value = "select q.id as questionId, k.id as knowledgeId, count(uq.id) as rightCount, " +
            "q.question_name as questionName FROM knowledge k LEFT JOIN question q ON q.knowledge_id = k.id " +
            " LEFT JOIN user_questions uq ON q.id = uq.question_id WHERE uq.user_question_status = 1 AND uq.user_id = ?1" +
            " GROUP BY q.question_name,q.id",nativeQuery = true)
    List<Object []> getUserRightCount(Integer userId);

    @Query(value="select knowledge_id from question where id=?1",nativeQuery = true)
    List<Object []> getKnowledgeId(Integer questionId);

    //拿到当前用户的作答反应表
    @Query(value="select knowledge_id, user_question_status, user_id FROM user_questions, question where user_id=?1 AND question_id = question.id"
            ,nativeQuery = true)
    List<Object []> ResponseList(Integer userId);




}
