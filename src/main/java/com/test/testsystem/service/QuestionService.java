package com.test.testsystem.service;


import com.test.testsystem.model.Question;
import com.test.testsystem.utils.JsonResult;

import java.util.List;

public interface QuestionService {

   Question getQuestionById(Integer id);

   JsonResult saveQuestion(Question question);

   JsonResult deleteQuestion(Integer id);

   List<Question> getQuestionList();


}
