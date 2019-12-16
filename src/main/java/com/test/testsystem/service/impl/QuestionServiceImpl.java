package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.QuestionRepos;
import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.model.Question;
import com.test.testsystem.model.User;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.EntityUtils;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        if(null==question.getId()||0==question.getId()){
            question.setUpdateTime(new Date());
            question.setCreateTime(new Date());
            question.setStatus(1);
        }
        return JsonResult.success(questionRepos.saveAndFlush(question));
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

    @Override
    public JsonResult getPageQuestionList(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Question>questions=questionRepos.findAll();
        PageInfo pageInfo=new PageInfo(questions);
        return JsonResult.success(pageInfo);

    }


    @Override
    public List<UserQuestionDto> getUserQuestionKnowledgeList(User user) {

        return EntityUtils.castEntity(questionRepos.getUserQuestionKnowledgeList(user.getId()),UserQuestionDto.class,new UserQuestionDto());

    }
}
