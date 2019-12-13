package com.test.testsystem.controller;

import com.test.testsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/questionDetail")
    public String questionDetail(){
        return "question/admin_question_detail";
    }

    @RequestMapping("/questionList")
    public String questionList(){
        return "question/admin_question_list";
    }
}
