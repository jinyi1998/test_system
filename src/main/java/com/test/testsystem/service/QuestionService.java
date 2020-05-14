package com.test.testsystem.service;


import com.test.testsystem.dto.CountedUserQUestion;
import com.test.testsystem.dto.KnowledgeQuestionCount;
import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.dto.UserQuestionRightCount;
import com.test.testsystem.model.Question;
import com.test.testsystem.model.User;
import com.test.testsystem.model.UserQuestions;
import com.test.testsystem.utils.JsonResult;

import java.util.List;

public interface QuestionService {

   Question getQuestionById(Integer id);

   JsonResult saveQuestion(Question question);

   JsonResult deleteQuestion(Integer id);

   List<Question> getQuestionList();

   JsonResult getPageQuestionList(Integer page,Integer pageSize);

   JsonResult getNextQuestion(Integer userId);

   List<UserQuestionDto> getUserQuestionKnowledgeList(User user);

   List<CountedUserQUestion> getCountedUserQuestionKnowledgeList(User user);

   List<KnowledgeQuestionCount> getKnowledgeQuestionCount(User user);

   List<UserQuestionRightCount> getUserQuestionRightCount(User user);

   JsonResult saveUserQuestion(UserQuestions userQuestions);


}
