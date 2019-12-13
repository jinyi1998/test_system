package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.KnowledgeRepos;
import com.test.testsystem.model.Knowledge;
import com.test.testsystem.service.KnowledgeService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepos knowledgeRepos;

    @Override
    public Knowledge getKnowledgeById(Integer id) {
        return knowledgeRepos.findById(id).get();
    }

    @Override
    public JsonResult saveKnowledge(Knowledge knowledge) {

        return JsonResult.success(knowledgeRepos.save(knowledge));
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
        //写完 会默认帮我们分页
        PageHelper.startPage(page,pageSize);
        List<Knowledge> knowledges = knowledgeRepos.findAll();
        PageInfo pageInfo = new PageInfo(knowledges);
        return JsonResult.success(pageInfo);
    }
}
