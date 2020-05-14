package com.test.testsystem.utils;

import com.test.testsystem.dao.KnowledgeRepos;
import com.test.testsystem.dao.QuestionRepos;
import com.test.testsystem.model.Question;
import com.test.testsystem.model.UserQuestions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserQuestionUtils {
    static QuestionRepos questionRepos;
    public static Integer getUserNearedQuestions(HashMap<Integer, List<UserQuestions>> map, List<Integer> questionIds, Integer userID, List<UserQuestions> userQuestionsWithAll){
        HashMap<Integer,Double> scoreMap = new HashMap<>();
        //1.先按照组分别评分
        for(Map.Entry<Integer,  List<UserQuestions>> entry : map.entrySet()){
            Integer mapKey = entry.getKey();
            List<UserQuestions> mapValue = entry.getValue();
            Double score = 10000.0;
            for (Integer i:questionIds){
                for (UserQuestions userQuestions:mapValue){
                    if (userQuestions.getQuestionId() == i){
//                        if (userID==userQuestions.getUserQuestionStatus())

                        //找到答题情况进行比对
                        for (UserQuestions u:userQuestionsWithAll){
                            if (u.getUserId()==userID&&u.getQuestionId()==i){
                                if (userQuestions.getUserQuestionStatus()==u.getUserQuestionStatus()){
                                    score = score + 0.25;
                                }else {

                                    score=score-0.25;
                                }
                            }
                        }
                    }else {
                       List<Object[]> a= questionRepos.getKnowledgeId(i);
                       List<Object[]> b= questionRepos.getKnowledgeId(userQuestions.getQuestionId());
                       if (a.equals(b)){
                           //知识点一样
                           for (UserQuestions u:userQuestionsWithAll){
                               if (u.getUserId()==userID&&u.getQuestionId()==i){
                                   if (userQuestions.getUserQuestionStatus()==u.getUserQuestionStatus()){
                                       score = score + 0.125;
                                   }else {

                                       score=score-0.125;
                                   }
                               }
                           }
                       }else {
                           score=score-0.25;
                       }

                    }
                }
            }
            scoreMap.put(mapKey,score);
        }
        //2.评分排序
        Double maxScore = 0.0;
        Integer userId = 0;
        for(Map.Entry<Integer, Double> entry : scoreMap.entrySet()){
            Integer mapKey = entry.getKey();
            Double score = entry.getValue();
            if (score > maxScore){
                maxScore = score;
                userId = mapKey;
            }
        }
        return userId;

    }
}
