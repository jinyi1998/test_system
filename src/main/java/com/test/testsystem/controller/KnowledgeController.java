package com.test.testsystem.controller;

import com.test.testsystem.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/knowledgeDetail")
    public String knowledgeDetail(){
        return "knowledge/admin_knowledge_detail";
    }

    @RequestMapping("/knowledgeList")
    public String knowledgeList(){
        return "knowledge/admin_knowledge_list";
    }
}
