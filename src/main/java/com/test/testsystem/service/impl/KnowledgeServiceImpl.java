package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.KnowledgeRepos;
import com.test.testsystem.dto.KnowledegeQuestionCount;
import com.test.testsystem.dto.UserQuestionRightCount;
import com.test.testsystem.model.ChartEntity;
import com.test.testsystem.model.Knowledge;
import com.test.testsystem.model.User;
import com.test.testsystem.model.UserQuestions;
import com.test.testsystem.service.KnowledgeService;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepos knowledgeRepos;
    @Autowired
    private QuestionService questionService;

    @Override
    public Knowledge getKnowledgeById(Integer id) {
        return knowledgeRepos.findById(id).get();
    }

    @Override
    public JsonResult saveKnowledge(Knowledge knowledge) {
        if (null == knowledge.getId() || 0 == knowledge.getId()){
            knowledge.setUpdateTime(new Date());
            knowledge.setCreateTime(new Date());
            knowledge.setStatus(1);
        }
        return JsonResult.success(knowledgeRepos.saveAndFlush(knowledge));
    }

    @Override
    public JsonResult deleteKnowledge(Integer id) {
        knowledgeRepos.deleteById(id);
        return JsonResult.success(null);
    }

    @Override
    public List<Knowledge> getKnowledgeList() {
        return knowledgeRepos.findAll();

    }

    @Override
    public JsonResult getPageKnowledgeList(Integer page, Integer pageSize) {
        //默认分页
        PageHelper.startPage(page,pageSize);
        List<Knowledge> knowledges = knowledgeRepos.findAll();
        PageInfo pageInfo = new PageInfo(knowledges);
        return JsonResult.success(pageInfo);
    }

    @Override
    public JsonResult getUserKnowledgeCharts(User user) {
        List<UserQuestionRightCount> rightCounts = questionService.getUserQuestionRightCount(user);

        List<KnowledegeQuestionCount> knowledegeQuestionCounts = questionService.getKnowledegeQuestionCount(user);
        List<ChartEntity> chartEntities = new ArrayList<>();
        for (KnowledegeQuestionCount k :knowledegeQuestionCounts){
            ChartEntity chartEntity = new ChartEntity();
            chartEntity.setXAxis(k.getKnowledgeName());
            Integer  right = 0;
            for (UserQuestionRightCount rightCount:rightCounts){
                if (k.getKnowledgeId() == rightCount.getKnowledgeId()){
                    right = right + rightCount.getRightCount();
                }
            }
            chartEntity.setValue((right/k.getTotalCount())+"");
            chartEntities.add(chartEntity);
        }
        return JsonResult.success(chartEntities);
    }
}
