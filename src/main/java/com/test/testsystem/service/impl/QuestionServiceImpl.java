package com.test.testsystem.service.impl;

import com.test.testsystem.dao.QuestionRepos;
import com.test.testsystem.model.Question;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepos questionRepos;

    @Override
    public Question getQuestionById(Integer id) {
        return questionRepos.findById(id).get();
    }

    @Override
    public JsonResult saveQuestion(Question question) {

        return JsonResult.success(questionRepos.save(question));
    }

    @Override
    public JsonResult deleteQuestion(Integer id) {
        questionRepos.deleteById(id);
        return JsonResult.success(null);
    }

    @Override
    public List<Question> getQuestionList() {
        return questionRepos.findAll();

    }
}
