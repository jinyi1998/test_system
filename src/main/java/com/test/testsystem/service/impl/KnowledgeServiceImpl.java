package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.KnowledgeRepos;
import com.test.testsystem.dao.UserQuestionRepos;
import com.test.testsystem.dto.CountedUserQUestion;
import com.test.testsystem.dto.KnowledgeQuestionCount;
import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.dto.UserQuestionRightCount;
import com.test.testsystem.model.ChartEntity;
import com.test.testsystem.model.Knowledge;
import com.test.testsystem.model.User;
import com.test.testsystem.service.KnowledgeService;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        if (null == knowledge.getId() || 0 == knowledge.getId()) {
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
        PageHelper.startPage(page, pageSize);
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Knowledge> knowledges = knowledgeRepos.findAll(pageable);
        return JsonResult.success(knowledges);
    }

    @Override
    public JsonResult getUserKnowledgeCharts(User user) {
        List<UserQuestionRightCount> rightCounts = questionService.getUserQuestionRightCount(user);

        List<CountedUserQUestion> countedUserQUestions = questionService.getCountedUserQuestionKnowledgeList(user);
        List<ChartEntity> chartEntities = new ArrayList<>();
        for (CountedUserQUestion c : countedUserQUestions) {
            ChartEntity chartEntity = new ChartEntity();
            chartEntity.setXAxis(c.getKnowledgeName());
            Integer right = 0;
            for (UserQuestionRightCount rightCount : rightCounts) {
                if (c.getKnowledgeId() == rightCount.getKnowledgeId().intValue()) {
                    right = right + rightCount.getRightCount().intValue();
                }
            }
            if (right == 0) {
                chartEntity.setValue("0");
            } else {
                chartEntity.setValue(getCal(right, c.getTotal().intValue()));
            }
            chartEntities.add(chartEntity);
        }
        return JsonResult.success(chartEntities);
//        List<KnowledgeQuestionCount> knowledgeQuestionCounts = questionService.getKnowledgeQuestionCount(user);
//        List<ChartEntity> chartEntities = new ArrayList<>();
//        for (KnowledgeQuestionCount k :knowledgeQuestionCounts){
//            ChartEntity chartEntity = new ChartEntity();
//            chartEntity.setXAxis(k.getKnowledgeName());
//            Integer  right = 0;
//            for (UserQuestionRightCount rightCount:rightCounts){
//                if (k.getKnowledgeId() == rightCount.getKnowledgeId().intValue()){
//                    right = right + rightCount.getRightCount().intValue();
//                }
//            }
//            if (k.getTotalCount().intValue() == 0){
//                chartEntity.setValue("0");
//            }else {
//                chartEntity.setValue(getCal(right,k.getTotalCount().intValue()));
//
//            }
//            chartEntities.add(chartEntity);
//        }
//        return JsonResult.success(chartEntities);


    }
    public String getCal (Integer v1, Integer v2){
        Double d = Double.valueOf(v1) / Double.valueOf(v2);
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d.toString();

    }


}
