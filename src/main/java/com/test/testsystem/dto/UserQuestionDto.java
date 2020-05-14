package com.test.testsystem.dto;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.math.BigInteger;

@Data
public class UserQuestionDto {
    private Integer userId;
    private Integer questionId;

    private String questionName;

    private Integer userQuestionStatus;

    private String knowledgeName;

    private Integer knowledgeId;

    private BigInteger questionTotal;

    public UserQuestionDto(){

    }

    public UserQuestionDto(Integer userId,Integer questionId,String questionName,Integer userQuestionStatus,String knowledgeName,Integer knowledgeId,BigInteger questionTotal){
        this.knowledgeName = knowledgeName;
        this.userId = userId;
        this.questionName = questionName;
        this.questionId = questionId;
        this.userQuestionStatus = userQuestionStatus;
        this.knowledgeId = knowledgeId;
        this.questionTotal=questionTotal;
    }


}
