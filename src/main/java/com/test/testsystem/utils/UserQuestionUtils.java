package com.test.testsystem.utils;

import com.test.testsystem.model.UserQuestions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserQuestionUtils {

    public static Integer getUserNearedQuestions(HashMap<Integer, List<UserQuestions>> map,List<Integer> questionIds){
        HashMap<Integer,Double> scoreMap = new HashMap<>();
        //1.先按照组分别评分
        for(Map.Entry<Integer,  List<UserQuestions>> entry : map.entrySet()){
            Integer mapKey = entry.getKey();
            List<UserQuestions> mapValue = entry.getValue();
            Double score = 100.0;
            for (Integer i:questionIds){
                for (UserQuestions userQuestions:mapValue){
                    if (userQuestions.getQuestionId() == i){
                        score = score + 1;
                    }else {
                        score = score - 0.1;
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
