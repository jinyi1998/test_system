package com.test.testsystem.controller;

import com.test.testsystem.model.Knowledge;
import com.test.testsystem.service.KnowledgeService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "getKnowledgeList",method = RequestMethod.POST)
    public JsonResult getKnowledgeList(Integer page,Integer pageSize){
        return knowledgeService.getPageKnowledgeList(page, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "saveKnowledge",method = RequestMethod.POST)
    public JsonResult saveKnowledge(Knowledge knowledge){
        return knowledgeService.saveKnowledge(knowledge);
    }

    @ResponseBody
    @RequestMapping(value = "deleteKnowledge",method = RequestMethod.POST)
    public JsonResult deleteKnowledge(Integer id){
        return knowledgeService.deleteKnowledge(id);
    }

    @ResponseBody
    @RequestMapping(value = "getKnowledge",method = RequestMethod.POST)
    public JsonResult getKnowledge(Integer id){
        return JsonResult.success(knowledgeService.getKnowledgeById(id));
    }
}
