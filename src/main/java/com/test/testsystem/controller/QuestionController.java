package com.test.testsystem.controller;

import com.test.testsystem.model.Question;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/questionDetail")
    public String questionDetail(@RequestParam("id") Integer id, Model model){
        if (null == id || 0 ==id){
            model.addAttribute("question",new Question());
        }else {
            model.addAttribute("question",questionService.getQuestionById(id));
        }
        return "question/admin_question_detail";
    }

    @RequestMapping("/questionList")
    public String questionList(){
        return "question/admin_question_list";
    }

    @ResponseBody
    @RequestMapping(value = "getQuestionList",method = RequestMethod.POST)
    public JsonResult getKnowledgeList(Integer page,Integer pageSize){
        return questionService.getPageQuestionList(page, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "saveQuestion",method = RequestMethod.POST)
    public JsonResult saveQuestion(Question question){
        return questionService.saveQuestion(question);
    }

    @ResponseBody
    @RequestMapping(value = "deleteQuestion",method = RequestMethod.POST)
    public JsonResult deleteQuestion(Integer id){
        return questionService.deleteQuestion(id);
    }

    @ResponseBody
    @RequestMapping(value = "getQuestion",method = RequestMethod.POST)
    public JsonResult getQuestion(Integer id){
        return JsonResult.success(questionService.getQuestionById(id));
    }
}
